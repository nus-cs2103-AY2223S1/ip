import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StoredTasks {
    static final String FILEPATH = "xx/xx.txt";

    public static void save(ArrayList<Task> storedTasks) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task task : storedTasks) {
                fw.write(task + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
