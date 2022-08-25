package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");

    private Path filePath;

    public Storage() {
        this.filePath = directoryPath.resolve("data.txt");
    }

    public Storage(String fileName) {
        this.filePath = directoryPath.resolve(fileName);
    }

    public TaskList loadFromFile() throws DukeException {
        try {
            File data = new File(filePath.toString());
            Scanner sc = new Scanner(data);
            List<Task> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String[] storedInfo = sc.nextLine().split(" \\| ");
                String type = storedInfo[0];
                boolean isDone = storedInfo[1].equals("O");
                String description = storedInfo[2];
                Task task;
                switch (type) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    task = new Deadline(description, isDone, Parser.parseDate(storedInfo[3]));
                    break;
                case "E":
                    task = new Event(description, isDone, Parser.parseDateTime(storedInfo[3]));
                    break;
                default:
                    throw new DukeException("OOPS!!! No save data found");
                }
                taskList.add(task);
            }

            sc.close();
            return new TaskList(taskList);
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!!! Data not found");
        }
    }

    public void storeToFile(TaskList taskList) throws DukeException {
        List<Task> dataToStore = taskList.getTaskList();
        ensureDirectoryExist();
        ensureFileExist();
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            for (int i = 0; i < taskList.getSize(); i++) {
                String dataToSave = dataToStore.get(i).taskToDataString();
                fw.write(dataToSave);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data cannot be stored");
        }
    }

    public void appendToFile(Task task) throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        try {
            FileWriter data = new FileWriter(filePath.toString(), true);
            data.write(task.taskToDataString());
            data.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! File cannot be open");
        }
    }

    private static void ensureDirectoryExist() {
        File folder = new File(directoryPath.toString());
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private void ensureFileExist() throws DukeException {
        try {
            File data = new File(directoryPath.resolve("data.txt").toString());
            if (!data.exists()) {
                data.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Unable to create new file. " +
                    "Tasks might not be stored.");
        }
    }
}
