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
     * Saves the given list of tasks in the specified file.
     *
     * @param list TaskList to be saved in the file.
     */
    public void save(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.flush();
            for (Task t : list.getList()) {
                if (t.toString().charAt(1) == 'D') {
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
            tempDir = dir.substring(0, filePath.indexOf('/'));
            Path folder = Paths.get(tempDir);
            dir = dir.substring(dir.indexOf('/') + 1);

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

    /**
     * Loads the data from the specified file into an arraylist. Throws
     * DukeException if file does not exist.
     *
     * @return Arraylist of tasks loaded from file.
     * @throws DukeException if file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>(); // to be returned

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String s = sc.nextLine();
                String description;
                char task = s.charAt(1);
                char symbol = s.charAt(4);
                boolean isDone = (symbol == 'X');

                if (task == 'T') {
                    description = s.substring(7);
                    Task t = new Todo(description);
                    if (isDone) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                    continue;
                }

                description = s.substring(7, s.indexOf('(') - 1);
                String time = s.substring(s.indexOf('(') + 5, s.indexOf(')'));
                if (task == 'D') {
                    Task t = new Deadline(description, time);
                    if (isDone) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                    continue;
                }

                if (task == 'E') {
                    Task t = new Event(description, time);
                    if (isDone) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }

            }
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
        return tasks;
    }
}
