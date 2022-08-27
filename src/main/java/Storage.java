import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void update(String str) {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(str);
            fw.close();
        }  // Catch block to handle the exception
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }
}
