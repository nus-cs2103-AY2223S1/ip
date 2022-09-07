package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads tasks from file or saves tasks into file.
 *
 * @author Lai Han Wen
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the specified file into an arraylist. Throws
     * DukeException if file does not exist.
     *
     * @return Arraylist of tasks loaded from file.
     * @throws DukeException if file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>(); // To be returned

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String s = sc.nextLine();
                char typeOfTask = s.charAt(1);
                char statusIcon = s.charAt(4);
                boolean isDone = (statusIcon == 'X');

                switch (typeOfTask) {
                case 'T':
                    tasks.add(this.getTodo(s, isDone));
                    break;
                case 'D':
                    tasks.add(this.getDeadline(s, isDone));
                    break;
                case 'E':
                    tasks.add(this.getEvent(s, isDone));
                    break;
                default:
                    assert false; // Execution should never reach this point!
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException();
        }

        return tasks;
    }

    private Task getTodo(String s, boolean isDone) {
        Task t = new Todo(this.getTodoDescription(s));
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    private Task getDeadline(String s, boolean isDone) {
        Task t = new Deadline(this.getDescription(s), this.getTime(s));
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    private Task getEvent(String s, boolean isDone) {
        Task t = new Event(this.getDescription(s), this.getTime(s));
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    private String getTodoDescription(String s) {
        int desStartIndex = 7;
        return s.substring(desStartIndex);
    }

    private String getDescription(String s) {
        int desStartIndex = 7;
        int desEndIndex = s.indexOf('(') - 1;
        return s.substring(desStartIndex, desEndIndex);
    }

    private String getTime(String s) {
        int timeStartIndex = s.indexOf('(') + 5;
        int timeEndIndex = s.indexOf(')');
        return s.substring(timeStartIndex, timeEndIndex);
    }

    /**
     * Saves the given list of tasks in the specified file.
     *
     * @param list TaskList to be saved in the file.
     */
    public void save(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.flush();
            ArrayList<Task> currList = list.getList();

            for (Task t : currList) {
                boolean isDeadline = (t.toString().charAt(1) == 'D');

                // Date of deadline saved must be in format yyyy-mm-dd; e.g. 2020-10-10
                if (isDeadline) {
                    Deadline d = (Deadline) t;
                    fw.write(d.toStringOri() + "\n");
                    continue;
                }

                fw.write(t + "\n");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates folder if folder does not exist, and does nothing if
     * folder already exists.
     */
    public void createFolder() {
        String dir = this.filePath;
        String tempDir;

        while (dir.contains("/")) {
            tempDir = dir.substring(0, filePath.indexOf('/')); // Get first directory
            Path folder = Paths.get(tempDir);
            dir = dir.substring(dir.indexOf('/') + 1); // Get next directory

            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                System.err.println("Failed to create folder!" + e.getMessage());
            }

        }
    }

    /**
     * Creates file in folder if file does not exist, and
     * does nothing if file already exists.
     */
    public void createFile() {
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Failed to create file!" + e.getMessage());
        }
    }
}
