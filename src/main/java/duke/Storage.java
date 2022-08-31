package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String taskListTxt = "duke.txt";

    /**
     * Writes {@code TaskList} taskList into data/duke.txt
     * Overwrites the whole file with current taskList
     * @param taskList the {@code TaskList} we are saving
     */
    public static void write(TaskList taskList) {
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                Parser.printMsg("Could not create /data directory");
            }
        }
        try {
            FileWriter fw = new FileWriter(String.format("./data/%s", taskListTxt));
            for (Task task : taskList) {
                fw.write(task.toData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Parser.printMsg(String.format("Failed to write file at ./data/%s", taskListTxt));
        }
    }

    /**
     * Loads {@code TaskList} taskList from data/duke.txt
     */
    public static TaskList load() {
        File f = new File(String.format("./data/%s", taskListTxt));
        TaskList tasks = new TaskList();
        try {
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    tasks.add(Parser.parseTask(sc.nextLine()));
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            Parser.printMsg(e.getMessage());
        }
        return tasks;
    }
}
