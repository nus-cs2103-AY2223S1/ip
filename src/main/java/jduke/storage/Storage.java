package jduke.storage;

import jduke.data.exception.JdukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
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
