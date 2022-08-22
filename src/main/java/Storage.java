import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private String storageName;
    private String storageDirname;
    private String filePath;

    public Storage(String storageName, String storageDirName) {
        this.storageName = storageName;
        this.storageDirname = storageDirName;
        this.filePath = String.format("%s%s%s", this.storageDirname, File.separator, this.storageName);
    }


    public void initializeStorage() throws IOException {
        Path filePath = Paths.get(this.filePath);
        Path dirPath = Paths.get(storageDirname);

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
            Ui.printCreateNewDirectory(this.storageDirname);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            Ui.printCreateNewDirectory(this.storageName);
        }
    }

    public void writeToStorage(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).toString() + "\n");
        }
        fw.close();
    }

    // TODO: Write this function
    public void readFromStorage() throws IOException {
    }
}
