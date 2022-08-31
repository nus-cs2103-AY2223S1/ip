package byu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import task.*;

public class Storage {

    private File file;
    private Scanner sc;
    private TaskList tasks;

    /**
     * Creates a FileReader that loads and stores data Executes the command.
     *
     * @return a FileReader instance
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        try {
            this.file = new File("./Duke.txt");
            this.sc = new Scanner(file);
            this.tasks = new TaskList();
        } catch (FileNotFoundException e) {
            String TEXT_FILE = "./Duke.txt";
            Path textFilePath = Paths.get(TEXT_FILE);
            Files.createFile(textFilePath);
            this.sc = new Scanner(file);
            this.tasks = new TaskList();
        }
    }

    /**
     * Loads the data of tasks previously added from the file.
     *
     * @return ToDoList that contains the previously added tasks.
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
