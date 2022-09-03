package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an object that tracks, loads, and saves tasks to the save file as changes are made.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a Task object from an individual line in the save file.
     *
     * @param line Individual line in the save file.
     * @return Task from the line if it is able to be parsed, else null.
     */
    private static Task parseTaskLine(String line) throws DukeException {
        String[] tokens = line.split("\\s+?", 2);
        if (tokens.length < 2) {
            throw new DukeException("Invalid save format");
        }
        int done = Integer.parseInt(tokens[0]);
        assert done == 1 || done == 0: "Save file lime format is invalid.";
        String commandInput = tokens[1];

        Task task = Parser.getTask(commandInput);
        if (done == 1) {
            task.mark();
        }
        return task;
    }

    /**
     * Returns an ArrayList containing all the parsed tasks.
     *
     * @return ArrayList containing all the parsed tasks.
     */
    public ArrayList<Task> load() {
        File f = new File(this.filePath);
        ArrayList<Task> res = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                Task t = parseTaskLine(line);
                if (t != null) {
                    res.add(t);
                }
                line = reader.readLine();
            }
            reader.close();
            return res;
        } catch (Exception e) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ioe) {
                System.out.println("IO error occurred while creating file.");
            } finally {
                return res;
            }
        }
    }

    /**
     * Saves all the tasks in the TaskList to a save file.
     *
     * @param taskList TaskList object containing all the tasks to be saved
     */
    public void save(TaskList taskList) {
        String res = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            try {
                String saveText = taskList.get(i).saveText();
                res += saveText + '\n';
            } catch (DukeException e) {
                System.out.println("Could not get task.");
            }
        }
        try {
            FileWriter writer = new FileWriter(this.filePath, false);
            writer.write(res);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error occurred while saving tasks.");
        }
    }
}
