package duke.storage;

import duke.common.DukeException;
import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle load & save user's task list
 *
 * @author Pontakorn Prasertsuk
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new Storage instance
     *
     * @param filePath the file path to be used
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Create a new file if it doesn't exist
     *
     * @throws DukeException if error occurs
     */
    private void createFileIfNotExist() throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create save file!");
        }
    }

    /**
     * Load the task list from the file
     *
     * @return the task list
     * @throws DukeException if error occurs
     */
    public List<Task> load() throws DukeException {
        createFileIfNotExist();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<Task> list = new ArrayList<Task>();

            String line = br.readLine();
            while (line != null) {
                list.add(Task.decode(line));
                line = br.readLine();
            }
            br.close();

            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!");
        } catch (IOException e) {
            throw new DukeException("Unable to read save file!");
        }
    }

    /**
     * Save the task list to the file
     *
     * @param list the task list to be saved
     * @throws DukeException if error occurs
     */
    public void save(List<Task> list) throws DukeException {
        createFileIfNotExist();

        try (PrintWriter pw = new PrintWriter(filePath, "UTF-8")) {
            list.forEach(task -> pw.println(task.encode()));
            pw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!");
        } catch (UnsupportedEncodingException e) {
            throw new DukeException("Unable to write save file!");
        }
    }
}
