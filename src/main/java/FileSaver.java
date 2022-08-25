import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaver {
    public static void newFile(String fileName) {
        File file = new File(fileName);
        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newDir(String dirName) {
        File file = new File(dirName);
        try {
            boolean value = file.mkdir();
            if (value) {
                System.out.println("Directory created");
            } else {
                System.out.println("Directory already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void save(ArrayList<Task> tasks, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (Task t : tasks) {
            writer.write(t.toString() + "\n");
        }
        writer.close();
    }
}
