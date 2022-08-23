import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveList(ArrayList<String> taskList) {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(this.filePath);

            for (String task : taskList) {
                fileWriter.write(task);
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> taskList = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            taskList.add(sc.nextLine());
        }
        sc.close();

        return taskList;
    }
}

