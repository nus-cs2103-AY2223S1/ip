import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

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
    protected void load() throws FileNotFoundException {
        if (this.file.exists() && !this.file.isDirectory()) {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(" \\| ");

                // Loads tasks into array list.
                try {
                    if (details[0].equals("T")) {
                        Duke.TASKS.add(new ToDo(details[2], details[1].equals("1")));
                    } else if (details[0].equals("D")) {
                        Duke.TASKS.add(new Deadline(details[2], details[3], details[1].equals("1")));
                    } else if (details[0].equals("E")) {
                        Duke.TASKS.add(new Event(details[2], details[3], details[1].equals("1")));
                    } else {
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
     */
    protected void save() {
        for (Task t : Duke.TASKS) {
            // 1 denotes task is done, 0 denotes task is not done.
            String taskDone = t.isDone ? "1" : "0";
            try {
                if (t instanceof ToDo) {
                    this.writeToFile("T | " + taskDone + " | " + t.taskName + "\n", false);
                } else if (t instanceof Deadline) {
                    this.writeToFile("D | " + taskDone + " | " + t.taskName + " | "
                            + ((Deadline) t).date + "\n", false);
                } else if (t instanceof Event) {
                    this.writeToFile("E | " + taskDone + " | " + t.taskName + " | "
                            + ((Event) t).date + "\n", false);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
