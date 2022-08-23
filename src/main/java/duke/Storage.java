package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String FILENAME = "saved-tasks.txt";
    private File filePath;
    private File dataFile;

    public Storage(String filePath) {
        try {
            this.filePath = new File(filePath);
            if (!this.filePath.exists()) {
                this.filePath.mkdir();
            }
            this.dataFile = new File(filePath + "/" + FILENAME);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData(TaskList tasks) {
        String csv = tasks.toCsv();
        try {
            FileWriter writer = new FileWriter(this.dataFile);
            writer.write(csv);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File load() {
        return dataFile;
    }
}
