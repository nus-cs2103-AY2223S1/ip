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
            throw new DukeException("Ensure Task is in this format\n\"D,1,Read book,Sunday");
        }
        if (command[1].equals("1")) {
            newTask.markComplete();
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
