package DukeBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the data storage.
 */
public class Storage {

    private File file;

    private Path path;

    public Storage(String filePath) {
        if (filePath.isBlank()) {
            path = Paths.get(System.getProperty("user.dir"), "src", "main", "tasks.txt");
        } else {
            path = Paths.get(System.getProperty("user.dir"), filePath);
        }
        file = new File(path.toUri());
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create .txt file.");
        }
    }

    /**
     * Loads the .txt file containing the tasks.
     *
     * @return Task list containing all the tasks in the .txt file.
     * @throws DukeException
     */
    public TaskList load() throws DukeException {
        Scanner scanner;
        TaskList taskList = new TaskList();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to read file.");
        }
        while (scanner.hasNext()) {
            String[] command = scanner.nextLine().split(",");
            Task newTask;
            switch (command[0]) {
            case "T":
                newTask = new ToDo(command[2]);
                break;
            case "D":
                newTask = new Deadline(command[2], command[3]);
                break;
            case "E":
                newTask = new Event(command[2], command[3]);
                break;
            default:
                throw new DukeException("    Ensure Task is in this format\n    \"D,1,Read book,Sunday");
            }
            if (command[1].equals("1")) {
                newTask.markComplete();
            }
            taskList.addTask(newTask);
        }
        return taskList;
    }

    /**
     * Writes the tasks in the task list into a .txt file.
     *
     * @param tasksToWrite The list of tasks to write.
     */
    public void write(TaskList tasksToWrite) {
        try {
            ArrayList<String> commandToWrite = new ArrayList<>();
            for (Task task : tasksToWrite) {
                String command = task.getTaskType() + ",";
                if (task.getStatusIcon().equals("X")) {
                    command += "1,";
                } else {
                    command += "0,";
                }
                command += task.getDescription();
                if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                    command = command + "," + task.getTime();
                }
                commandToWrite.add(command);
            }
            Files.write(path, commandToWrite);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

}
