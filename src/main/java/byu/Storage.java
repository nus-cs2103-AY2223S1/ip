package byu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


/**
 * Represents a storage that deals with loading tasks from the file
 * and saving tasks in a file.
 */
public class Storage {

    private File file;
    private Scanner sc;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Storage with a file.
     * Creates the file if the file is not found.
     *
     * @throws IOException if an I/O error occurs.
     */
    public Storage(Ui ui) throws IOException {
        try {
            this.file = new File("./Duke.txt");
            this.sc = new Scanner(file);
            this.ui = ui;
            this.tasks = new TaskList(this.ui);
        } catch (FileNotFoundException e) {
            String TEXT_FILE = "./Duke.txt";
            Path textFilePath = Paths.get(TEXT_FILE);
            Files.createFile(textFilePath);
            this.sc = new Scanner(file);
            this.tasks = new TaskList(this.ui);
        }
    }

    /**
     * Loads the data of tasks previously added from the file.
     *
     * @return TaskList that contains the previously added tasks.
     */
    public TaskList load() {
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] details = line.split(" \\| ");
            Task t;
            switch (details[0]) {
            case "D":
                t = new Deadline(details[2], details[3]);
                break;
            case "E":
                t = new Event(details[2], details[3]);
                break;
            case "T":
                t = new ToDo(details[2]);
                break;
            default:
                t = new Task("unknown");
            }
            if (details[1].equals("1")) {
                t.setDone(true);
            }
            tasks.addTask(t);
        }
        sc.close();
        return this.tasks;
    }

}
