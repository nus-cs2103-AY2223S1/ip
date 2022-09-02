package neo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String inp;
    public Storage() {
    }
    public Storage(String inp) {
        this.inp = inp;
    }
    private Task task;

    /**
     * prints tasks in file
     *
     * @param filePath stores path of file
     * @throws FileNotFoundException
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * writes task to file
     *
     * @param filePath stores path of file
     * @param textToAppend string representation of task
     * @throws IOException input output exception
     */
    private static void WriteToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * adds task to file
     *
     * @param filePath stores path of file
     * @param textToAppend string representation of task
     * @throws IOException
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    File f = new File("/Users/richavm/Documents/NUS/Y2S1/CS2103T/data/Neo.txt");

    /**
     * calls appendToFile to add to file
     *
     * @param task task
     * @throws IOException input output exception
     */
    public void storeData(Task task) throws IOException {
        //Task task = new Task(inp);
        try {
            appendToFile(f.getAbsolutePath(), task.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * calls WriteToFile to write task to file
     *
     * @param task task
     * @throws IOException input output exception
     */
    public void writeData(Task task) throws IOException {
        //Task task = new Task(inp);
        try {
            WriteToFile(f.getAbsolutePath(), task.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Prints data from file.
     *
     * @throws IOException input output exception
     */
    public void retrieveData() throws IOException {
        //Task task = new Task(inp);
        try {
            printFileContents(f.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
