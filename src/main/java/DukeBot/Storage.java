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
        assert file != null;
        assert path != null;
        Scanner scanner;
        TaskList taskList = new TaskList();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to read file.");
        }
        while (scanner.hasNext()) {
            loadLineToDuke(scanner, taskList);
        }
        return taskList;
    }

    /**
     * Loads a line in the storage file to Duke bot.
     *
     * @param scanner Scanner object that scans the storage file.
     * @param taskList TaskList object that carries all the tasks in bot.
     * @throws DukeException When a line in the storage file is not valid, DukeException is thrown
     */
    private void loadLineToDuke(Scanner scanner, TaskList taskList) throws DukeException {
        String[] command = scanner.nextLine().split(",");
        Task newTask;
        String taskDescription = command[2];
        String date;
        String priority;
        switch (command.length) {
        case 4:
            date = null;
            priority = command[3];
            break;
        case 5:
            date = command[3];
            priority = command[4];
            break;
        default:
            throw new DukeException("Ensure Task is in this format\n\"D,1,Read book,2000-12-31,H");
        }
        switch (command[0]) {
        case "T":
            newTask = new ToDo(taskDescription);
            break;
        case "D":
            newTask = new Deadline(taskDescription, date);
            break;
        case "E":
            newTask = new Event(taskDescription, date);
            break;
        default:
            throw new DukeException("Ensure Task is in this format\n\"D,1,Read book,2000-12-31,H");
        }
        if (command[1].equals("1")) {
            newTask.markComplete();
        }
        switch (priority) {
        case "L":
            newTask.setPriority(Priority.LOW);
        case "M":
            newTask.setPriority(Priority.MEDIUM);
        case "H":
            newTask.setPriority(Priority.HIGH);
        }
        taskList.addTask(newTask);
    }

    /**
     * Writes the tasks in the task list into a .txt file.
     *
     * @param tasksToWrite The list of tasks to write.
     */
    public void write(TaskList tasksToWrite) {
        assert file != null;
        assert path != null;
        try {
            ArrayList<String> commandToWrite = tasksToWrite.convertTasksToList();
            Files.write(path, commandToWrite);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

}
