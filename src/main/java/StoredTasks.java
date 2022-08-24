import java.io.*;
import java.util.ArrayList;

public class StoredTasks {
    private static final String FILEDIR = "data";
    private static final String FILEPATH = FILEDIR + File.separator + "duke.txt";


    public static ArrayList<Task> load() {
        ArrayList<Task> storedTasks = new ArrayList<>(100);
        try {
            File dir = new File(FILEDIR);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(FILEPATH);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return storedTasks;
    }

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
