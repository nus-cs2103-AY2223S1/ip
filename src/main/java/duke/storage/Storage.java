package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Storage {

    private final String path;
    private final String pathAlias;

    public Storage(String pathTask, String pathAlias) {
        this.path = pathTask;
        this.pathAlias = pathAlias;
    }

    /**
     * Returns the tasks stored on the disk.
     *
     * If no database was found, an empty ArrayList will be returned.
     *
     * @return Tasks stored on disk.
     */
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

    /**
     * Updates the database to reflect any changes made by the user.
     *
     * @param tasks The <Code>ArrayList</Code> of tasks.
     */
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

    /**
     * Loads all aliases made by the user.
     *
     * @return A Hashmap containing the aliases.
     */
    public HashMap<String, String> loadAliases() {
        HashMap<String, String> aliases = new HashMap<>();
        File db = new File(pathAlias);

        try {
            Scanner myScanner = new Scanner(db);
            while (myScanner.hasNextLine()) {
                String row = myScanner.nextLine();
                int idx = row.indexOf('=');

                assert idx != -1;

                String alias = row.substring(0, idx);
                String command = row.substring(idx + 1);

                aliases.put(alias, command);
            }
        } catch (FileNotFoundException e) {
            // No pre-existing aliases - Add in some
            aliases.put("ls", "list");
            aliases.put("rm", "delete");
            aliases.put("t", "todo");
            aliases.put("d", "deadline");
            aliases.put("e", "deadline");
        }

        return aliases;
    }

    /**
     * Updates the aliases in storage.
     *
     * @param aliases The Hashmap containing the aliases.
     */
    public void updateAlias(HashMap<String, String> aliases) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var entry : aliases.entrySet()) {
            stringBuilder.append(entry.getKey()).append('=').append(entry.getValue()).append('\n');
        }
        try {
            FileWriter writer = new FileWriter(pathAlias);
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to store your aliases :(");
        }
    }
}
