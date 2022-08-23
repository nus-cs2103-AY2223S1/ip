import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private Path path;

    public Storage(String path) {
        this.path = Paths.get(path);
        initialize();
    }

    private void initialize() {
        checkDirectory();
        checkFile();
    }

    public void write(String content) {
        initialize();
        try {
            FileWriter fileWriter = new FileWriter(this.path.toFile());
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ioException) {
            throw new DukeException.WriteFileException(this.path, "Unable to write the file.");
        }
    }

    public String read() {
        String result = "";
        initialize();
        try {
            Scanner scanner = new Scanner(this.path);
            while (scanner.hasNextLine()) {
                result = result + scanner.nextLine() + System.lineSeparator();
            }
        } catch (IOException ioException) {
            throw new DukeException.ReadFileException(this.path, "Unable to write the file.");
        }
        return result;
    }

    private void checkDirectory() {
        File temp = this.path.getParent().toFile();
        if (!temp.exists()) {
            temp.mkdirs();
        }
    }

    private void checkFile() {
        File temp = this.path.toFile();
        try {
            if (!temp.exists()) {
                temp.createNewFile();
            }
        } catch (IOException ioException) {
            throw new DukeException.ReadFileException(this.path, "Unable to create new files.");
        }
    }
}
