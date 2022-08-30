package john.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import john.data.exception.JohnException;

/**
 * Represents the storage for the tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for the Storage class.
     * @param filePath The file path of the data storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the storage file from the file path if it exists, else creates a storage file.
     * @return The storage file if it exists, else returns a empty storage file.
     * @throws IOException The storage directory or file cannot be created.
     */
    private File loadFile() throws IOException {
        String directoryPath = this.filePath.substring(0, this.filePath.lastIndexOf("/"));
        File storageFile;
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("cannot create storage directory");
            }
        }
        storageFile = new File(this.filePath);
        if (!storageFile.exists()) {
            if (!storageFile.createNewFile()) {
                throw new IOException("cannot create storage file");
            }
        }
        return storageFile;
    }

    /**
     * Reads the storage file and converts it into a ArrayList of the stored tasks.
     * @return ArrayList of stored tasks.
     * @throws JohnException The storage file cannot be loaded.
     */
    public ArrayList<String> load() throws JohnException {
        ArrayList<String> storedTasks = new ArrayList<>();
        try (Scanner sc = new Scanner(loadFile())) {
            while (sc.hasNext()) {
                String task = sc.nextLine();
                storedTasks.add(task);
            }
        } catch (IOException e) {
            throw new JohnException("cannot load storage file");
        }
        return storedTasks;
    }

    /**
     * Saves all the tasks into the storage file.
     * @param tasks ArrayList of tasks in their storage format.
     * @throws JohnException The file cannot be found or written to.
     */
    public void saveAllTasks(ArrayList<String> tasks) throws JohnException {
        try (FileWriter fw = new FileWriter(this.filePath, false)) {
            StringBuilder sb = new StringBuilder();
            for (String task : tasks) {
                sb.append(String.format("%s%n", task));
            }
            fw.write(String.valueOf(sb));
        } catch (FileNotFoundException e) {
            throw new JohnException("cannot find file");
        } catch (IOException e) {
            throw new JohnException("unable to write to storage");
        }
    }
}
