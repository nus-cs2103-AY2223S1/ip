import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Objects;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class FileManager {
    // Reused from https://www.w3schools.com/java/java_files_create.asp
    // Reused from https://www.w3schools.com/java/java_files_read.asp
    public static ArrayList<Task> createFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File myObj = new File("./data/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName() + "\n");
                return tasks;
            } else {
                System.out.println("Successfully from file that exists \n");
                return readFile(myObj, tasks);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToFile(ArrayList<Task> data) {
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            for (int i = 0; i < data.size(); i++) {
                myWriter.write(data.get(i).toFileString() + System.getProperty("line.separator"));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static ArrayList<Task> readFile(File myObj, ArrayList<Task> tasks) {
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] output = data.split(" , ", 0);
                boolean isDone = Integer.parseInt(output[1]) == 1;
                if (Objects.equals(output[0], "E")) {
                    tasks.add(new Event(output[2], output[3], isDone));
                } else if (Objects.equals(output[0], "D")) {
                    tasks.add(new Deadline(output[2], output[3], isDone));
                } else if (Objects.equals(output[0], "T")) {
                    tasks.add(new ToDo(output[2], isDone));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return tasks;
    }
}
