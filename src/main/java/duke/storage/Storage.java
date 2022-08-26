package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            File parentFolder = file.getParentFile();
            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }
            Scanner sc = new Scanner(file);
            // If file is empty, return empty ArrayList
            if (!sc.hasNextLine()) {
                return tasks;
            }
            String input = sc.nextLine();
            while (true) {
                String[] indTask = input.split(" \\| ");
                String taskType = indTask[0];
                switch (taskType) {
                case "T":
                    Todo todo = new Todo(indTask[2]);
                    todo.isDone = indTask[1].equals("1");
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadlineTask = new Deadline(indTask[2], indTask[3]);
                    deadlineTask.isDone = indTask[1].equals("1");
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    Event eventTask = new Event(indTask[2], indTask[3]);
                    eventTask.isDone = indTask[1].equals("1");
                    tasks.add(eventTask);
                    break;
                default:
                    // For the case of errors in the starting letters of every new line in file.
                    throw new DukeException("Invalid task type");
                }
                if (!sc.hasNextLine()) {
                    break;
                }
                input = sc.nextLine();
            }
        } catch (IOException e) {
            throw new DukeException("File not found!");
        }
        return tasks;
    }

    public void writeToFile(TaskList tasklist) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            for (Task task : tasklist.tasks) {
                fw.write(task.inputToTxt());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to file!");
        }
    }
}
