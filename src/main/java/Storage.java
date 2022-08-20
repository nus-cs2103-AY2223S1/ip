import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Storage {

    private String fileName;

    public Storage(String path) {

        this.fileName = path;

        try {
            File file = new File(this.fileName);
            file.createNewFile(); // Creates a new file if the file doesn't exist, does nothing otherwise
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeToFile(String content) throws IOException {
        Files.writeString(Path.of(this.fileName), content, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public String readFromFile() throws IOException {
        return Files.readString(Path.of(this.fileName));
    }

}
