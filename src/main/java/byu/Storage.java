package byu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

import exceptions.DuplicateException;
import exceptions.IncorrectFileInputException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


/**
 * A storage that deals with loading tasks from the file
 * and saving tasks to a file.
 */
public class Storage {

    private static final String TEXT_FILE = "./Duke.txt";
    private File file;
    private Scanner scanner;
    private final TaskList tasks;
    private Ui ui;

    /**
     * Creates a Storage with a file.
     * Creates the file if the file is not found.
     *
     * @throws IOException if an I/O error occurs.
     */
    public Storage(Ui ui) throws IOException {
        try {
            this.ui = ui;
            this.file = new File(TEXT_FILE);
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            Path textFilePath = Paths.get(TEXT_FILE);
            Files.createFile(textFilePath);
            this.file = new File(TEXT_FILE);
            this.scanner = new Scanner(file);
        } finally {
            this.tasks = new TaskList(this.ui);
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
            Task task = getTaskFromFile(nextLine);
            tasks.addTask(task);
        }
        scanner.close();
        return this.tasks;
    }

    private Task getTaskFromFile(String nextLine) throws IncorrectFileInputException {
        String[] details = nextLine.split(" \\| ");
        String symbol = details[0];
        String isDoneValue = details[1];
        String taskName = details[2];
        String description;
        Task task;
        switch (symbol) {
        case Deadline.SYMBOL:
            description = details[3];
            LocalDateTime dateTime = LocalDateTime.parse(description);
            task = new Deadline(taskName, dateTime);
            break;
        case Event.SYMBOL:
            description = details[3];
            task = new Event(taskName, description);
            break;
        case ToDo.SYMBOL:
            task = new ToDo(taskName);
            break;
        default:
            throw new IncorrectFileInputException();
        }
        if (isDoneValue.equals("1")) {
            task.setDone(true);
        }
        return task;
    }

    /**
     * Updates the file with the current state of the Tasklist.
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
            System.out.print(e.getMessage());
        }
    }

}
