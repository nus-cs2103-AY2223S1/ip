package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private ArrayList<Task> log;
    private BufferedWriter writer;
    private BufferedReader reader;

    private final String filePath;

    Storage() {
        this.filePath = "data/tasks.txt";
    }

    Storage(String filePath) {
        this.filePath = Objects.requireNonNullElse(filePath, "data/tasks.txt");
    }

    /**
     * Goes through the save file, loading data.
     *
     * @param t TaskList in which data is loaded to.
     * @throws IOException  If an error arises from file access.
     * @throws DukeException If something goes wrong with commands sent to Duke.
     */
    public void load(TaskList t) throws IOException, DukeException {

        String first;
        String rest = filePath;
        boolean isTextFile = false;

        while (!isTextFile) {
            if (rest.contains("/")) {
                int index = rest.indexOf('/');
                first = rest.substring(0, index).trim();
                rest = rest.substring(index + 1).trim();

                File d = new File(first);
                if (!d.getAbsoluteFile().exists()) {
                    new File(first).mkdirs();
                }
            } else {
                isTextFile = true;
            }
        }

        File saveFile = new File(filePath);
        saveFile.createNewFile();

        // Creating the file reader
        reader = new BufferedReader(new FileReader(filePath));
        File f = new File(filePath);
        if (f.getAbsoluteFile().exists()) {
            tryLoading();
        } else {
            log = new ArrayList<>();
        }
        reader.close();

        t.setLog(log);
    }

    /**
     * Helper method for load.
     *
     * @throws IOException  If there is a file access error.
     * @throws DukeException  If something went wrong when parsing.
     */
    private void tryLoading() throws IOException, DukeException {
        log = new ArrayList<>();
        String s;
        char first;
        while ((s = reader.readLine()) != null) {
            first = s.charAt(0);
            parse(first, s);
        }
    }

    /**
     * Calls correct Task subclass based on letter in text.
     *
     * @param c Character indicating what Task type data s contains.
     * @param s Task data.
     * @throws DukeException  If invalid data is used to create a task.
     */
    private void parse(char c, String s) throws DukeException {
        switch (c) {
        case 'T':
            log.add(Todo.load(s));
            break;
        case 'D':
            log.add(Deadline.load(s));
            break;
        case 'E':
            log.add(Event.load(s));
            break;
        default:
            throw new DukeException("Something went wrong with reading the save");
            //Fallthrough
        }
    }

    /**
     * Goes through the TaskList, saving all tasks to the file.
     *
     * @param t TaskList to be saved.
     * @throws IOException  If an error occurs from file access.
     */
    public void save(TaskList t) throws IOException {
        log = t.getLog();

        writer = new BufferedWriter(new FileWriter("data\\tasks.txt", false));
        for (Task task : log) {
            writer.write(task.saveString());
            writer.newLine();
        }
        writer.close();
    }


}
