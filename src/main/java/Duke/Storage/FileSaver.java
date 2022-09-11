package Duke.Storage;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import Duke.Tasks.TaskList;

public class FileSaver {

    private Path filePath;

    public FileSaver (String fileName) {
        String dirPath = System.getProperty("user.dir");
        this.filePath = Paths.get(dirPath, "src", "test", "artifacts", "ip_jar", "data", fileName + ".txt");
    }

    public void save(TaskList tasks) {

        try {
            FileWriter writer = new FileWriter(this.filePath.toString());
            writer.write(tasks.save());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
