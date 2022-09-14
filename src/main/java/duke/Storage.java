package duke;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;


public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from a file.
     *
     * @return An ArrayList of tasks
     * @throws DukeException The exception to be thrown when an error occurs.
     * @throws FileNotFoundException The exception to be thrown if the file is not found.
     */
    public ArrayList<Task> load() throws DukeException, FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<Task>();
        File f = new File(filePath);
        Scanner fileScanner = new Scanner(f);

        while (fileScanner.hasNext()) {

            String[] input = fileScanner.nextLine().split(" \\| ");
            Task task;

            switch (input[0]) {
            case "D":
                task = new Deadline(input[2], LocalDateTime.parse(input[4]));
                task.setTag(input[3]);
                break;

            case "E":
                task = new Event(input[2], LocalDateTime.parse(input[4]));
                task.setTag(input[3]);
                break;

            case "T":
                task = new Todo(input[2]);
                task.setTag(input[3]);
                break;

            default:
                throw new DukeException("Duke couldn't read this line! " + input[0]);
            }

            if (input[1].equals("1")) {
                task.mark();
            };

            tasks.add(task);
        }
        fileScanner.close();
        return tasks;
    }

    /**
     * Save tasks into a file.
     *
     * @param tasks Array of Tasks to be saved.
     * @throws IOException The exception to be thrown if the file is not found.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.getWriteString() + "\n");
        }
        fw.close();
    }

}
