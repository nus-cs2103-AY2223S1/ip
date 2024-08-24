package zeus.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import zeus.exception.ZeusException;
import zeus.task.Deadline;
import zeus.task.Event;
import zeus.task.Task;
import zeus.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage.
     *
     * @param filePath Relative file path to file containing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the hard disk when Zeus starts up.
     *
     * @return ArrayList of tasks.
     * @throws ZeusException If error encountered when loading data.
     */
    public ArrayList<Task> load() throws ZeusException {

        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                return taskList;
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] words = line.split(" \\| ");
                Task t;
                switch (words[0]) {
                case "T":
                    t = new Todo(words[2]);
                    break;
                case "D":
                    t = new Deadline(words[2], words[3]);
                    break;
                case "E":
                    t = new Event(words[2], words[3]);
                    break;
                default:
                    throw new ZeusException("☹ Could not read file.");
                }
                if (Integer.parseInt(words[1]) == 1) {
                    t.markAsDone();
                }
                taskList.add(t);

            }
            s.close();
        } catch (IOException e) {
            throw new ZeusException("☹ File not found or folder does not exist yet.");
        }
        return taskList;
    }


    /**
     * Saves the tasks in the hard disk automatically whenever the task list changes.
     *
     * @param taskList List of tasks to be saved.
     * @throws ZeusException If error is encountered while saving to file.
     */
    public void saveTasksToDisk(ArrayList<Task> taskList) throws ZeusException {

        try {
            File f = new File(this.filePath);
            File parent = f.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            BufferedWriter fw = new BufferedWriter(new FileWriter(f));
            for (Task t : taskList) {
                fw.append(t.getFileFormat());
                fw.append("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new ZeusException("Error writing to file.");
        }
    }
}
