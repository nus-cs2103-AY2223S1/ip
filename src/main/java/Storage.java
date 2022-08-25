package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File dataDir;
    private File history;

    public Storage(String filePath) {
        this.history = new File(filePath);
        this.dataDir = history.getParentFile();
        //when new storage object made, create the storage data files
        this.createRequiredFiles();
    }

    public void createRequiredFiles() {
        //will not create if files and directory already exist
        this.dataDir.mkdirs();
        try {
            this.history.createNewFile();
        } catch (IOException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException("An important file or directory was modified.");
        }
    }

    public void update(TaskList taskList) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(history);
            //prepare what to overwrite
            String overwrite = "";
            for (Task task : taskList) {
                overwrite += task.toData() + "\n";
            }
            fileWriter.write(overwrite);
            fileWriter.close();
        } catch (IOException e) {
            //made directory and/or made file could be deleted by user during runtime of this program
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        try {
            FileWriter clearer = new FileWriter(this.history);
            clearer.write("");
            clearer.close();
        } catch (IOException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException("An important file or directory was modified.");
        }
    }

    public File getHistory() {
        return this.history;
    }
}
