package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.ParsingTaskException;
import duke.task.*;

/**
 * The interface responsible for handling the storing and loading of user data from disk.
 */
public class Storage {
    private String dataPath;

    /**
     * Constructor for Storage.
     * @param dataPath the file path to a .txt file to store and load data from.
     */
    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    private static Task parse(String data) throws ParsingTaskException {
        String[] components = data.split(",");

        if (components.length == 0) {
            throw new ParsingTaskException("Data was empty or not formatted properly.");
        }

        String type = components[0];

        if (type.equals("T")) {
            return Todo.parse(data);
        } else if (type.equals("D")) {
            return Deadline.parse(data);
        } else if (type.equals("E")) {
            return Event.parse(data);
        } else {
            throw new ParsingTaskException(String.format("duke.Task.Task was of unknown type: %s", type));
        }
    }

    /**
     * Loads the user's task list from disk.
     * @return the user's task list. If the file does not exist, an empty one is created.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(this.dataPath);

            file.getParentFile().mkdirs();
            file.createNewFile();

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                try {
                    tasks.add(Storage.parse(data));
                } catch (ParsingTaskException e) {
                    System.out.println(e);
                    continue;
                }
            }

            scanner.close();

            return tasks;
        } catch (Exception e) {
            System.out.println("An error occurred.\n" + e);
        } finally {
            return tasks;
        }
    }

    public void save(TaskList tasks) {
        try {
            // Create new file
            String content = "";

            for (Task t : tasks.getAll()) {
                content += t.toSaveString() + "\n";
            }

            File file = new File(this.dataPath);

            // If the file doesn't exist, then create it
            file.getParentFile().mkdirs();
            file.createNewFile();


            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Write in file
            bw.write(content);

            // Close connection
            bw.close();

            System.out.println("Saved tasks list successfully!");
        } catch (Exception e) {
            System.out.println(e + this.dataPath);
        }
    }
}
