package ava.util;

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
 * Utility class that handles reading from and writing to device storage.
 */
public class Storage {
    private final String filePath;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Storage.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the program file from the specified file path if the file exists, else creates the file
     * and its directories. Returns an ArrayList of tasks constructed from data in the file.
     *
     * @return ArrayList of tasks read from the file.
     * @throws IOException If file is not found.
     */
    public ArrayList<Task> read() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File myData = new File(filePath);
            Scanner scanner = new Scanner(myData);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] task = input.split(" \\| ");
                boolean isDone = task[1].equals("1");
                if (task[0].equals("T")) {
                    tasks.add(new Todo(task[2], isDone));
                } else if (task[0].equals("D")) {
                    tasks.add(new Deadline(task[2], isDone, LocalDateTime.parse(task[3], timeFormat)));
                } else if (task[0].equals("E")) {
                    tasks.add(new Event(task[2], isDone, LocalDateTime.parse(task[3], timeFormat)));
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
     * Writes tasks with a specified format to the file in the specified file path.
     *
     * @param tasks An ArrayList of tasks to write.
     */
    public void write(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            for (Task t : tasks) {
                fileWriter.write(t.formatChange() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
