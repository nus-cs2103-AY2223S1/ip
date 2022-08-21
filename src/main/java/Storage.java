import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected File file;

    public Storage(String filePath) {
        try {
            Files.createDirectories(Paths.get(filePath));
            this.file = new File(filePath+ "/duke.txt");

            if (!file.exists()) {
                boolean result = file.createNewFile();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                list.add(TaskCreator.createFromStorage(line));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
