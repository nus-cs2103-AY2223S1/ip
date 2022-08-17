import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        try {
            Files.createDirectories(Paths.get(this.filePath.substring(0, this.filePath.lastIndexOf('/'))));
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Directory or file cannot be located. New file is created.");
        }
    }

    public List<String> loadLocalData() throws DukeException {
        List<String> data = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            throw new DukeException("File is inaccessible");
        }
    }

    public void saveLocalData(List<String> data) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(String.join(System.lineSeparator(), data));
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
