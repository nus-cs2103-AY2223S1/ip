package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File db = new File(path);

        try {
            Scanner myScanner = new Scanner(db);
            while (myScanner.hasNextLine()) {
                // TODO: Bug where "|" may be part of the description
                String[] data = myScanner.nextLine().split(" \\| ");
                Task task;
                try {
                    switch (data[0]) {
                    case "T":
                        task = new Todo(data[2]);
                        break;

                    case "E":
                        task = new Event(data[2], LocalDate.parse(data[3]));
                        break;

                    case "D":
                        task = new Deadline(data[2], LocalDate.parse(data[3]));
                        break;

                    default:
                        continue;
                    }
                } catch (DukeException e) {
                    // Invalid data, just ignore the row
                    continue;
                }

                if (data[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // Nothing to do if no existing file, just return the empty array list.
        }

        return tasks;
    }

    public void update(ArrayList<Task> tasks) {
        StringBuilder store = new StringBuilder();

        for (Task task : tasks) {
            store.append(task.encode()).append("\n");
        }

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(store.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to store your files :(");
        }
    }
}
