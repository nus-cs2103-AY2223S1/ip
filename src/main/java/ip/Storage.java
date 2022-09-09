package ip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    protected List<Task> loadFile() throws DukeException {
        try {
            // Creates the output List
            List<Task> taskList = new ArrayList<>();

            File f = new File(this.filePath);

            // Create Duke.txt if it doesn't exist.
            if (!f.exists()) {
                boolean fileCreated = f.createNewFile();
                if (!fileCreated) {
                    throw new DukeException("DukeError: Storage file could not be created!");
                }
            }
            // Else read from Duke.txt and convert text to Task.
            else {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    Task newTask = loadTask(sc.nextLine());
                    taskList.add(newTask);
                }
                sc.close();
            }

            return taskList;
        } catch (IOException e) {
            throw new DukeException("DukeError: Error loading file!");
        }
    }

    private Task loadTask(String taskStr) throws DukeException {
        String[] taskArray = taskStr.split("\\|");
        String taskTypeStr = taskArray[0];
        String isDoneStr = taskArray[1];
        String description = taskArray[2];

        boolean isDone = isDoneStr.equals("X");

        Task newTask;
        switch (taskTypeStr) {
        case "T":
            newTask = new ToDo(description, isDone);
            break;
        case "D":
            String by = taskArray[3];
            newTask = new Deadline(description, by, isDone);
            break;
        case "E":
            String at = taskArray[3];
            newTask = new Event(description, at, isDone);
            break;
        default:
            // Throws
            throw new DukeException(String.format("Could not load task: %s", description));
        }

        return newTask;
    }

    protected void storeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save all data!");
        }

    }
}