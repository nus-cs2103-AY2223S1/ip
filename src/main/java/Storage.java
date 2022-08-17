import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<String> loadLocalData() throws IOException {
        Files.createDirectories(Paths.get("data/"));
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        List<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            data.add(scanner.nextLine());
        }
        scanner.close();
        return data;
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
