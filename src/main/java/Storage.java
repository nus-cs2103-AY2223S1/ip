import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String directoryPath = "./data";
    private static String fileName = "ted.txt";

    /**
     * Load tasks from storage, return empty array if file not found
     * or cannot be opened.
     *
     * @return ArrayList<Task>
     */
    public static ArrayList<Task> loadTasks() throws InvalidEncodingException {
        File file = new File(directoryPath + "/" + fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            Scanner s = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                String encoded = s.nextLine();
                tasks.add(Task.decode(encoded));
            }

            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static String encodeTasks(ArrayList<Task> tasks) {
        String result = "";
        for (Task task : tasks) {
            result += task.encode() + "\n";
        }
        return result;
    }

    /**
     * Save tasks to storage.
     *
     * @param tasks
     * @throws IOException
     * @throws SecurityException
     */
    public static void saveTasks(ArrayList<Task> tasks) throws IOException, SecurityException {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(directoryPath + "/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(directoryPath + "/" + fileName);
        fw.write(encodeTasks(tasks));
        fw.close();
    }
}
