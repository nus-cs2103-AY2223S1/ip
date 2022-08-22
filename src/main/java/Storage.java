import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

public class Storage {
    public final String fileName;
    public final String filePath;
    public final File myFile;

    Storage(String fileName) {
        this.fileName = fileName;
        this.filePath = "./data/" + fileName;
        this.myFile = new File(this.filePath);
    }

    public void write(String text) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Uh uh! The system cannot find the path specified. Are you sure your file path is correct?");
        }
    }
}
