package kirby;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import kirby.tasks.Deadline;
import kirby.tasks.Event;
import kirby.tasks.Task;
import kirby.tasks.Todo;

/**
 * Storage class handles writing into and reading from the text file created to store previous user's entries.
 */
public class Storage {
    private File file;
    private final String filePath;
    private final File dir;

    /**
     * Constructor of the Storage class.
     *
     * @param filePath Name of the file path as a string.
     * @param dirPath Name of the directory path as a string.
     * @throws IOException If there is an error with user's input.
     */
    public Storage(String filePath, String dirPath) throws IOException {
        this.filePath = filePath;
        this.dir = new File(dirPath);
        this.file = new File(filePath);
        if (!file.exists()) {
            file = createFile();
        }
    }

    /**
     * Creates a file if the file has not been created.
     *
     * @return New file created.
     */
    public File createFile() {
        try {
            dir.mkdir();
            if (file.createNewFile()) {
                return file;
            } else {
                System.out.println("File already exists.");
                return null;
            }
        } catch (IOException e) {
            System.out.println("File not created");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes the list of task into a file.
     *
     * @throws IOException If there is an error with writing into the file.
     */
    public void writeTask(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(task.toFileOutput() + "\n");
        }
        writer.close();
    }

    /**
     * Converts the status icon into boolean isCompleted.
     *
     * @param statusIcon the String form of a status icon.
     * @return true if the task is completed, otherwise false.
     */
    public static boolean convertToIsCompleted(String statusIcon) {
        if (statusIcon.equals("[ ]")) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Reads from a file and returns the list of tasks that is written in the file.
     *
     * @throws FileNotFoundException If the file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> previousTasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                switch (parts[0]) {
                case "kirby.tasks.Todo":
                    previousTasks.add(new Todo(parts[1], convertToIsCompleted(parts[2])));
                    break;
                case "kirby.tasks.Deadline":
                    previousTasks.add(new Deadline(parts[1], parts[2], convertToIsCompleted(parts[3])));
                    break;
                case "kirby.tasks.Event":
                    previousTasks.add(new Event(parts[1], parts[2], convertToIsCompleted(parts[3])));
                    break;
                default:
                    throw new IOException();
                }
            }
            return previousTasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
