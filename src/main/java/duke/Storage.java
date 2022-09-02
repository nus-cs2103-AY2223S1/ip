package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A Storage handles loading tasks from a file and saving tasks to the file.
 */
public class Storage {
    private File directory;
    private File file;

    /**
     * Constructor method for a Storage.
     *
     * @param dirName name of the directory of the file
     * @param fileName name of the file
     */
    public Storage(String dirName, String fileName) {
        this.directory = new File(dirName);
        this.file = new File(dirName + "/" + fileName);
    }

    /**
     * Gets the list of tasks from the file.
     *
     * @return the list of tasks
     * @throws FileNotFoundException if the attempt to open the file failed
     * @throws DukeException if an invalid task is encountered in the file
     */
    public ArrayList<Task> getTasksFromDisk() throws FileNotFoundException, DukeException {
        ArrayList<Task> list = new ArrayList<>();
        if (this.file.exists()) {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] taskArr = line.split(" \\| ");
                if (taskArr[0].equals("T")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    Todo t = new Todo(description);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    list.add(list.size(), t);
                } else if (taskArr[0].equals("D")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Deadline t = new Deadline(description, date);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    list.add(list.size(), t);
                } else if (taskArr[0].equals("E")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Event t = new Event(description, date);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    list.add(list.size(), t);
                } else {
                    throw new DukeException("Oops, unknown task type.");
                }
            }
            System.out.println("Loaded tasks.");
        }
        return list;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param list list of tasks
     * @throws IOException if an I/O error occurs when saving tasks to the file
     */
    public void saveTasks(ArrayList<Task> list) throws IOException {
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < list.size(); i++) {
            fw.write(list.get(i).save() + System.lineSeparator());
        }
        fw.close();
    }
}
