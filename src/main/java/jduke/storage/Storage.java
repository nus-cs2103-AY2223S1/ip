package jduke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import jduke.data.exception.JdukeException;

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
            boolean wasDirectoryCreated = directory.mkdirs();
            if (!wasDirectoryCreated) {
                throw new IOException("cannot create storage directory");
            }
        }
        storageFile = new File(this.filePath);
        if (!storageFile.exists()) {
            boolean isFileCreated = storageFile.createNewFile();
            if (!isFileCreated) {
                throw new IOException("cannot create storage file");
            }
        }
        return storageFile;
    }

    /**
     * Reads the storage file and converts it into a ArrayList of the stored tasks.
     * @return ArrayList of stored tasks.
     * @throws JdukeException The storage file cannot be loaded.
     */
    public ArrayList<String> load() throws JdukeException {
        ArrayList<String> storedTasks = new ArrayList<>();
        try {
            File storageFile = loadFile();
            Scanner sc = new Scanner(storageFile);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                storedTasks.add(task);
            }
            sc.close();
        } catch (IOException e) {
            throw new JdukeException("cannot load storage file");
        }
        return storedTasks;
    }

    /**
     * Saves all the tasks into the storage file.
     * @param tasks ArrayList of tasks in their storage format.
     * @throws JdukeException The file cannot be found or written to.
     */
    public void saveAllTasks(ArrayList<String> tasks) throws JdukeException {
        try {
            StringBuilder sb = new StringBuilder();
            for (String task : tasks) {
                sb.append(String.format("%s%n", task));
            }
            FileWriter fw = new FileWriter(this.filePath, false);
            fw.write(String.valueOf(sb));
            fw.close();
        } catch (FileNotFoundException e) {
            throw new JdukeException("cannot find file");
        } catch (IOException e) {
            throw new JdukeException("unable to write to storage");
        }
    }
}
