package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage that stores the task list in a file.
 */
public class Storage {

    private String filePath;
    /**
     * Creates a new storage that stores the task list in the given file.
     * @param filePath the path to the file where the task list is stored
     */
    public Storage(String filePath) {
        String[] path = filePath.split("/");
        String fileName = path[path.length - 1];
        String[] directoryName = filePath.split(fileName);
        newDir(directoryName[0]);
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the file.
     * @return the task list as an ArrayList of tasks
     * @throws IOException if the file cannot be read or opened
     * @throws DukeException if the file is not in the correct format
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" \\| ");
            if (task.length <= 1) {
                throw new DukeException("☹ OOPS!!! The file is corrupted.");
            }
            switch (task[0]) {
            case "T":
                tasks.add(new Todo(task[2]));
                break;
            case "D":
                tasks.add(new Deadline(task[2], task[3]));
                break;
            case "E":
                tasks.add(new Event(task[2], task[3]));
                break;
            }
            if (task[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    /**
     * Creates a new file with the given name in the given directory.
     * @param fileName the name of the file and path to be created
     */
    public static void newFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new directory with the given name.
     * @param dirName the name of the directory to be created
     */
    public static void newDir(String dirName) {
        File file = new File(dirName);
        try {
            file.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves the Tasklist to the file.
     * @param tasklist the Tasklist to be saved
     * @throws IOException if the file cannot be written to
     */
    public void save(TaskList tasklist) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        ArrayList<Task> tasks = tasklist.getTasks();
        for (Task t : tasks) {
            writer.write(t.fileFormat() + "\n");
        }
        writer.close();
    }
}
