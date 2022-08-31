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

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void WriteToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    File f = new File("/Users/richavm/Documents/NUS/Y2S1/CS2103T/data/Neo.txt");

    public void Storedata(Task task) throws IOException {
        //Task task = new Task(inp);
        try {
            appendToFile(f.getAbsolutePath(), task.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void Writedata(Task task) throws IOException {
        //Task task = new Task(inp);
        try {
            WriteToFile(f.getAbsolutePath(), task.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void Retrievedata() throws IOException {
        //Task task = new Task(inp);
        try {
            printFileContents(f.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
