package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents storage for tasks.
 *
 * @author Elgin
 */
public class Storage {
    /** Path to file where tasks are stored. */
    private final Path filePath;

    /** File reference where tasks are stored. */
    private final File file;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path to file (e.g. 'src/data/tasks.txt')
     */
    public Storage(String filePath) {
        String[] directories = filePath.split("/");
        this.filePath = Paths.get(System.getProperty("user.dir"), directories);
        this.file = new File(this.filePath.toString());

        // Path to directory where file that stores tasks is at
        Path dirPath = Paths.get(System.getProperty("user.dir"), Arrays.copyOf(directories, directories.length - 1));

        // Making sure that missing folders / files are created.
        File dir = new File(dirPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!this.file.exists()) {
            try {
                this.writeToFile("", true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from data/duke.txt file.
     *
     * @throws FileNotFoundException If file cannot be opened by Scanner.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tempList = new ArrayList<>();

        if (this.file.exists() && !this.file.isDirectory()) {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(" \\| ");

                try {
                    switch (details[0]) {
                    case "T":
                        tempList.add(new ToDo(details[2], details[1].equals("1")));
                        break;
                    case "D":
                        tempList.add(new Deadline(details[2], details[3], details[1].equals("1")));
                        break;
                    case "E":
                        tempList.add(new Event(details[2], details[3], details[1].equals("1")));
                        break;
                    default:
                        throw new DukeException("File contains lines that cannot be validated as a Task.");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            try {
                this.writeToFile("", true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return tempList;
    }

    /**
     * Writes to file that contains all the tasks.
     *
     * @param textToAdd The text to be added to the file.
     * @throws IOException If there are errors in input/output to the file.
     */
    private void writeToFile(String textToAdd, boolean isOverwrite) throws IOException {
        FileWriter fw = isOverwrite
                ? new FileWriter(this.filePath.toString())
                : new FileWriter(this.filePath.toString(), true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves tasks into a duke.txt file.
     *
     * @param tasks All the tasks to save.
     */
    public void save(TaskList tasks) {
        for (Task t : tasks.getTasks()) {
            // 1 denotes task is done, 0 denotes task is not done.
            String taskDone = t.isDone() ? "1" : "0";
            try {
                if (t instanceof ToDo) {
                    this.writeToFile("T | " + taskDone + " | " + t.getTaskName() + "\n", false);
                } else if (t instanceof Deadline) {
                    this.writeToFile("D | " + taskDone + " | " + t.getTaskName() + " | "
                            + ((Deadline) t).getDate() + "\n", false);
                } else if (t instanceof Event) {
                    this.writeToFile("E | " + taskDone + " | " + t.getTaskName() + " | "
                            + ((Event) t).getDate() + "\n", false);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
