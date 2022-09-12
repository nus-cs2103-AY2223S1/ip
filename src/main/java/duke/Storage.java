package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Storage is used to store and read Tasks.
 */
public class Storage {
    private String filepath;
    private ArrayList<Task> tasks = new ArrayList<>();

    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return
     */
    public List<Task> load() throws DukeException {
        parseFileToTasks(filepath);
        return tasks;
    }

    /**
     * @param taskList TaskList object to be stored in the file
     * @throws IOException when the BufferedWritter is unable to initialise properly
     */
    public void save(TaskList taskList) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(taskList.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts all lines of tasks stored in the text file to Task Object
     *
     * @param filepath location of file stored
     */
    private void parseFileToTasks(String filepath) throws DukeException {
        new File(filepath).getParentFile().mkdir(); // create directory if it does not exist
        Scanner in;
        try {
            in = new Scanner(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new DukeException("Storage file is not found");
        }
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] data = line.split(" \\| ");
            Task task;
            switch (data[0]) {
            case "T":
                task = new Todo(data[2]);
                break;
            case "D":
                task = new Deadline(data[2], data[3]);
                break;
            case "E":
                task = new Event(data[2], data[3]);
                break;
            default:
                throw new DukeException("Something is wrong is the tasks stored in file");
            }
            if (data[1].equals("X")) {
                task.mark();
            }
            this.tasks.add(task);
        }
    }

    @Override
    public String toString() {
        String out = "";
        for (Task t : tasks) {
            out += t.toFileString() + "\n";
        }
        return out;
    }
}
