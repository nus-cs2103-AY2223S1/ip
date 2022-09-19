package neo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class
 */
public class Storage {
    protected String inp;
    private File f;

    /**
     * Constructor for storage class.
     */
    public Storage() {

    }

    /**
     * Constructor for storage class with parameter inp.
     *
     * @param inp Stores user input string.
     */
    public Storage(String inp) {

    }

    private static Task task;

    /**
     * Prints tasks in file.
     *
     * @param filePath Stores path of file.
     * @throws FileNotFoundException
     */
    public static String printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String str = "";
        int counter = 1;
        while (s.hasNext()) {
            str += counter + ". " + s.nextLine() + "\n";
            counter++;
            s.nextLine();
        }
        if (str.equals("")) {
            return "No tasks in task manager";
        }
        return str;
    }


    /**
     * Writes task to file.
     *
     * @param filePath Stores path of file.
     * @param textToAppend String representation of task.
     * @throws IOException Input output exception.
     */
    private static void WriteToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Adds task to file.
     *
     * @param filePath Stores path of file.
     * @param textToAppend String representation of task.
     * @throws IOException Input output exception.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    //File f = new File("data/Neo.txt");
    private static final String FOLDER = "data";
    private String filePath = "data/Neo.txt";

    public void createFile() throws IOException {
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls appendToFile to add to file.
     *
     * @param task Stores task.
     * @throws IOException Input output exception.
     */

    public void storeData(Task task) throws IOException {
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            appendToFile(f.getAbsolutePath(), task.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Calls WriteToFile to write task to file.
     *
     * @param task Stores task.
     * @throws IOException Input output exception.
     */

    public void writeData(Task task) throws IOException {
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            WriteToFile(f.getAbsolutePath(), task.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Prints data from file.
     *
     * @throws IOException Input output exception.
     */

    public String retrieveData() throws IOException {
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return printFileContents(f.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return "retrieve data";
    }
}
