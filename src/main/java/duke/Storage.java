package duke;

import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for storing and retrieving save data for Duke
 */
public class Storage {
    File file;

    /**
     * Constructs a Storage.
     *
     * @param filePath file path of the save data
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads the tasks from the save data.
     *
     * @return an ArrayList of tasks retrieved from the save data
     */
    public ArrayList<Task> load() {
        ArrayList<Task> res = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                res.add(fileLineToTask(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("./data"));
                File f = new File("data/tasks.txt");
            } catch (IOException ex) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        return res;
    }

    /**
     * Refreshes the save data.
     *
     * @param tasks the TaskList to obtain new data from.
     */
    public void refresh(TaskList tasks) {
        File temp = new File("data/temp.txt");
        try {
            FileWriter fw = new FileWriter(temp);
            fw.write(tasks.toFileFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        this.file.delete();
        temp.renameTo(new File("data/tasks.txt"));
        this.file = temp;
    }

    /**
     * Adds a new task to the save data.
     *
     * @param task task to be added to the save data
     */
    public void addTaskToStorage(Task task) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(task.toFileFormat() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Readings a line from the save data and converts it to a task.
     *
     * @param fileLine the file line to be read
     * @return a task based on the file line
     */
    public static Task fileLineToTask(String fileLine) {
        String delimiter = " \\| ";
        String[] strings = fileLine.split(delimiter, 4);
        boolean isDone = strings[1].equals("1");
        if (strings[0].equals("T")) {
            return new ToDo(strings[2], isDone);
        } else if (strings[0].equals("D")) {
            return new Deadline(strings[2], isDone, LocalDate.parse(strings[3]));
        } else {
            return new Event(strings[2], isDone, LocalDate.parse(strings[3]));
        }
    }
}
