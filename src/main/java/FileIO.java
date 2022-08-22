import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for input and output
 * to the file in the storage.
 */
public class FileIO {
    private static final String FOLDER_PATH = "data";
    private static final String FILE_PATH = "data/Tumu.txt";

    public static List<Task> loadData() {
        File file = getFile();
        List<Task> userTasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            //Each line in the file is an individual task.
            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                //Figure out what type of command this is, add back to userTasks.
                Task task = new TaskFormatter(taskStr).getTask();
                userTasks.add(task);
            }
            sc.close();
        } catch (TumuException e) {
            System.out.println(e);
        } finally {
            return userTasks;
        }
    }

    public static void saveData(List<Task> userTasks) {
        try {
            getFile();
            FileWriter fileWriter = new FileWriter(FILE_PATH);

            //Add contents to file.
            for (Task task : userTasks) {
                fileWriter.write(task.parse() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("\tAn I/O error has occurred.");
            e.printStackTrace();
        }
    }

    private static File getFile() {
        /**
         * Checks for the file and return it.
         * If the file or folder it is contained in does not exist,
         * then make the folder and the file.
         */

        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("\tFolder is created for data storage!\n");
        }

        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("\tFile is created for data storage!\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return file;
        }
    }
}