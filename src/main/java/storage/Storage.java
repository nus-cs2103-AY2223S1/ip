package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import ui.Ui;
import parser.Parser;
import tasks.Task;
import tasks.TaskList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author fannyjian
 */
public class Storage {

    private File storageFile;

    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    /**
     * Reads from hard disk and converts saved tasks into a list of tasks,
     * or creates a new file and initialises task list if file has not been created yet.
     *
     * @param ui UI of the program.
     * @return List of stored tasks (if any).
     */
    public ArrayList<Task> load(Ui ui) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(storageFile));
            Stream<String> content = reader.lines();

            content.forEach(s -> handleString(s, tasks));

            ui.setLoaded();
        } catch (FileNotFoundException e1) {
            storageFile.getParentFile().mkdirs();
            storageFile.createNewFile();

            FileWriter writer = new FileWriter(storageFile);
            writer.write("  Luna finds the following items saved in your list 🍃");
            writer.close();
        } finally {
            return tasks;
        }
    }

    public void handleString(String s, ArrayList<Task> tasks) {
        Task tsk = Parser.parseSaved(s);
        if (tsk != null) {
            tasks.add(tsk);
        }
    }

    /**
     * Writes to the hard disk every time the task list is updated.
     *
     * @param tasks List of tasks saved by user.
     */
    public void updateStorage(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(storageFile);
            String content = "  Luna finds the following items saved in your list 🍃";
            content = content.concat(tasks.stored());

            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("⚡️Luna has encountered an error while updating tasks⚡️" +
                                "\n️Please exit and try again ️⛈");
        }
    }

}
