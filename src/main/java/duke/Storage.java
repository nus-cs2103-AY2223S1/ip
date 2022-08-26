package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from a file.
     *
     * @return
     * @throws DukeException
     * @throws FileNotFoundException
     */

    public ArrayList<Task> load() throws DukeException, FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<Task>();
        File f = new File(filePath);
        Scanner fileScanner = new Scanner(f);

        while (fileScanner.hasNext()) {
            String[] input = fileScanner.nextLine().split(" \\| ");
            Task task = new Task("");

            switch (input[0]) {
                case "D":
                    task = new Deadline(input[2], LocalDateTime.parse(input[3]));
                    break;

                case "E":
                    task = new Event(input[2], LocalDateTime.parse(input[3]));
                    break;

                case "T":
                    task = new Todo(input[2]);
                    break;

                default:
                    throw new DukeException("Duke couldn't understand what this file type is! " + input[0]);
            }
            if (input[1].equals("1")) {
                task.mark();
            }
            tasks.add(task);
        }
        fileScanner.close();
        return tasks;
    }

    /**
     * Save tasks into a file.
     *
     * @param tasks
     * @throws IOException
     */

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.getWriteString() + "\n");
        }
        fw.close();
    }

}
