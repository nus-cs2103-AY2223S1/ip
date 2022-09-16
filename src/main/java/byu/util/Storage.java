package byu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import byu.exceptions.DuplicateException;
import byu.exceptions.IncorrectFileInputException;
import byu.task.Task;

/**
 * A storage that deals with loading tasks from the file
 * and saving tasks to a file.
 */
public class Storage {

    private static final String TEXT_FILE = "./Byu.txt";
    private File file;
    private Scanner scanner;
    private final TaskList tasks;

    /**
     * Creates a Storage with a file.
     * Creates the file if the file is not found.
     *
     * @throws IOException if an I/O error occurs.
     */
    public Storage() throws IOException {
        try {
            this.file = new File(TEXT_FILE);
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            Path textFilePath = Paths.get(TEXT_FILE);
            Files.createFile(textFilePath);
            this.file = new File(TEXT_FILE);
            this.scanner = new Scanner(file);
        } finally {
            this.tasks = new TaskList();
        }
    }

    /**
     * Loads the data of tasks previously added from the file.
     *
     * @return a TaskList that contains the tasks in the file.
     * @throws IncorrectFileInputException if tasks in the file cannot be loaded to the TaskList.
     * @throws DuplicateException if two same tasks are found in the file.
     */
    public TaskList load() throws IncorrectFileInputException, DuplicateException {
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            Task task = Parser.parseFileToTask(nextLine);
            tasks.addTask(task);
        }
        scanner.close();
        return this.tasks;
    }

    /**
     * Updates the file with the current state of the TaskList.
     */
    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
                Task task = tasks.getTask(i);
                task.write(fileWriter);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
