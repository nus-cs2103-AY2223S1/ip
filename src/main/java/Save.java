import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Save class implements the storage feature.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Save {
    private static final File dir = new File("data/");
    private static final File textFile = new File("data/kirby.txt");

    public static File alreadyExist() {
        return textFile;
    }

    public static File createDataFile() throws IOException {
        try {
            dir.mkdir();
            if (textFile.createNewFile()) {
                System.out.println("File created: " + textFile.getName());
                return textFile;
            } else {
                System.out.println("File already exists.");
                return null;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static void writeTask(ArrayList<Task> Tasks, File textFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
        for (Task task : Tasks) {
            writer.write(task.toFileOutput() + "\n");
        }
        writer.close();
    }

    public static boolean checkIfCreated() {
        File directory = new File("data/");
        File file = new File("data/kirby.txt");
        System.out.println(directory.exists() && file.exists());
        return (directory.exists() && file.exists());
    }

    public static ArrayList<Task> readFromFile() throws FileNotFoundException {
        ArrayList<Task> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split("~");
                System.out.println(parts[0]);
                switch (parts[0]) {
                case "Todo":
                    result.add(new Todo(parts[1]));
                    break;
                case "Deadline":
                    result.add(new Deadline(parts[1], parts[2]));
                    break;
                case "Event":
                    result.add(new Event(parts[1], parts[2]));
                    break;
                }
            }
            return result;
    } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
