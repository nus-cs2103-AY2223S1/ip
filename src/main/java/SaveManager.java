import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveManager {
    public static String FILE_PATH = "data/duke.txt";

    private String path;

    public SaveManager(String path) {
        this.path = path;
        File saveFile = new File(this.path);
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Cannot create new save");
            }
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> output = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(Path.of(this.path));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read from save");
            return output;
        }
        while (sc.hasNextLine()) {
            String[] saveLine = sc.nextLine().split(Task.SAVE_SEPARATOR);
            switch (saveLine[0]) {
            case "T":
                output.add(new ToDo(saveLine[2], saveLine[1].equals("1")));
                break;
            case "E":
                output.add(new Event(saveLine[2], LocalDate.parse(saveLine[3]), saveLine[1].equals("1")));
                break;
            case "D":
                output.add(new Deadline(saveLine[2], LocalDate.parse(saveLine[3]), saveLine[1].equals("1")));
                break;
            }
        }
        return output;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            for (Task t : taskList) {
                fileWriter.write(t.toSave());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to write to file");
        }
    }
}
