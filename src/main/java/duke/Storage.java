package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * The Storage class encapsulates the loading of tasks from a hard disk and saving to of tasks to a hard disk.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves tasks from the hard disk and stores them in a list.
     *
     * @param taskList List where tasks are to be loaded to.
     * @throws FileNotFoundException
     */
    public void loadTasks(TaskList taskList) throws FileNotFoundException {
        File f = new File(this.filePath);
        assert f.exists();
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            Task t = Parser.parseFromFile(sc.nextLine());
            taskList.append(t);
        }
    }

    /**
     * Saves tasks to the hard disk.
     *
     * @param taskList List from which tasks are to be saved to the hard disk.
     * @throws IOException
     */
    public void saveTasks(TaskList taskList) throws IOException {
        int length = taskList.length();
        FileWriter fw = new FileWriter(this.filePath);
        if (length > 0) {
            fw.write(taskList.index(0).toString() + "\n");
            fw.close();
            FileWriter secondFw = new FileWriter(this.filePath, true);
            for (int i = 1; i < length; i++) {
                secondFw.write(taskList.index(i).toString() + "\n");
            }
            secondFw.close();
        } else {
            fw.write("");
            fw.close();
        }
    }
}
