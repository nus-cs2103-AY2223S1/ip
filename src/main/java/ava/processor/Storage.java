package ava.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.Todo;


/**
 * Class that represents storage to load tasks from file and save tasks in the file.
 */
public class Storage {
    private final String FILE_PATH;
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Storage.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Returns an ArrayList of tasks according to what it reads from the file.
     *
     * @return ArrayList of tasks read from the file.
     * @throws IOException If file is not found.
     */
    public ArrayList<Task> read() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File myData = new File(FILE_PATH);
            Scanner scanner = new Scanner(myData);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] task = input.split(" \\| ");
                boolean isDone = task[1].equals("1");
                if (task[0].equals("T")) {
                    tasks.add(new Todo(task[2], isDone));
                } else if (task[0].equals("D")) {
                    tasks.add(new Deadline(task[2], isDone, LocalDateTime.parse(task[3], TIME_FORMAT)));
                } else if (task[0].equals("E")) {
                    tasks.add(new Event(task[2], isDone, LocalDateTime.parse(task[3], TIME_FORMAT)));
                }
            }
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("Folder does not exist");
            } else if (new File("data/ava.txt").createNewFile()) {
                System.out.println("I cannot find the file ava.txt");
            }
        }
        return tasks;
    }

    /**
     * Writes tasks with a specified format to the file.
     *
     * @param task An ArrayList of tasks to write.
     */
    public void write(ArrayList<Task> task) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, false);
            for (Task t : task) {
                fileWriter.write(t.formatChange() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
