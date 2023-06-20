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
    private final String notePath;

    /**
     * Constructs a new Storage instance
     *
     * @param filePath the file path to be used
     */
    public Storage(String filePath, String notePath) {
        this.filePath = filePath;
        this.notePath = notePath;
    }

    /**
     * Creates a new file if it doesn't exist
     *
     * @throws DukeException if error occurs
     */
    private void createFileIfNotExist(String path) throws DukeException {
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            assert file.exists();
        } catch (IOException e) {
            throw new DukeException("Unable to create save file!");
        }
    }

    /**
     * Loads the task list from the file
     *
     * @return the task list
     * @throws DukeException if error occurs
     */
    public List<Task> load() throws DukeException {
        createFileIfNotExist(filePath);

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
     * Saves the task list to the file
     *
     * @param list the task list to be saved
     * @throws DukeException if error occurs
     */
    public void save(List<Task> list) throws DukeException {
        createFileIfNotExist(filePath);

        try (PrintWriter pw = new PrintWriter(filePath, "UTF-8")) {
            list.forEach(task -> pw.println(task.encode()));
            pw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!");
        } catch (UnsupportedEncodingException e) {
            throw new DukeException("Unable to write save file!");
        }
    }

    /**
     * Loads note from the file
     * 
     * @param key note name
     * @return note content
     * @throws DukeException if error occurs
     */
    public String loadNote(String key) throws DukeException {
        String currentNotePath = notePath + "/" + key + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(currentNotePath))) {
            String note = "";

            String line = br.readLine();
            while (line != null) {
                note += line + "\n";
                line = br.readLine();
            }
            br.close();

            return note;
        } catch (FileNotFoundException e) {
            throw new DukeException("Note file not found!");
        } catch (IOException e) {
            throw new DukeException("Unable to read note file!");
        }
    }

    /**
     * Saves note to the file
     * 
     * @param key note name
     * @param note note content
     * @throws DukeException if error occurs
     */
    public void saveNote(String key, String note) throws DukeException {
        String currentNotePath = notePath + "/" + key + ".txt";
        createFileIfNotExist(currentNotePath);

        try (PrintWriter pw = new PrintWriter(currentNotePath, "UTF-8")) {
            pw.println(note);
            pw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Note file not found!");
        } catch (UnsupportedEncodingException e) {
            throw new DukeException("Unable to write note file!");
        }
    }

    /**
     * Deletes note from the file
     * 
     * @param key note name
     * @throws DukeException if error occurs
     */
    public void deleteNote(String key) throws DukeException {
        String currentNotePath = notePath + "/" + key + ".txt";

        try {
            File file = new File(currentNotePath);
            if (file.exists()) {
                file.delete();
            } else {
                throw new DukeException("Note file not found!");
            }
        } catch (Exception e) {
            throw new DukeException("Unable to delete note file!");
        }
    }
}
