package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


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
        ArrayList<Task> tasks = new ArrayList<>();
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
                        t.setDone(true);
                    }
                    tasks.add(tasks.size(), t);
                } else if (taskArr[0].equals("D")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Deadline t = new Deadline(description, date);
                    if (done.equals("X")) {
                        t.setDone(true);
                    }
                    tasks.add(tasks.size(), t);
                } else if (taskArr[0].equals("E")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Event t = new Event(description, date);
                    if (done.equals("X")) {
                        t.setDone(true);
                    }
                    tasks.add(tasks.size(), t);
                } else {
                    throw new DukeException("Oops, unknown task type.");
                }
            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks list of tasks
     * @throws IOException if an I/O error occurs when saving tasks to the file
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).save() + System.lineSeparator());
        }
        fw.close();
    }
}
