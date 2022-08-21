import java.io.IOException;
import java.io.FileWriter;

public class FileWriting {
    /**
     * Class to update .txt file
     */
    public static void update(String filePath, TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(list.getTasks());
            fw.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear(String filePath) {
        update(filePath, new TaskList());
    }
}
