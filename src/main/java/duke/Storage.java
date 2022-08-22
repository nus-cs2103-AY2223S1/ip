package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a Task object from an individual line in the save file.
     *
     * @param line Individual line in the save file.
     * @return Task from the line if it is able to be parsed, else null.
     */
    private static Task parseTaskLine(String line) {
        String[] tokens = line.split("\\|");
        Task t;
        try {
            String type = tokens[0];
            int done = Integer.parseInt(tokens[1]);
            String meta = tokens[2];
            switch (type) {
                case "T": t = new Todo(meta); break;
                case "D": t = new Deadline(meta); break;
                case "E": t = new Event(meta); break;
                default: throw new Exception();
            }
            if (done == 1) {
                t.mark();
            }
            return t;
        } catch (Exception e) {
            return null;
        }
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
                // HANDLE ERROR
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
            }
        }
        try {
            FileWriter writer = new FileWriter(this.filePath, false);
            writer.write(res);
            writer.close();
        } catch (Exception e) {
            // HANDLE ERROR
        }
    }
}
