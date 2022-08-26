package duke.storage;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String FILE_PATH;
    private File f;
    private ArrayList<String> tasksList;

    public Storage(String filePath) {
        FILE_PATH = filePath;
        f = new File(FILE_PATH);
        tasksList = new ArrayList<>();
    }

    public ArrayList<String> load() {
        try {
            if (!f.createNewFile()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String curr = sc.nextLine();
                    tasksList.add(curr);
                }
                sc.close();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return tasksList;
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.write(System.lineSeparator());
        fw.close();
    }

    public void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(f);
        writer.print("");
        writer.close();
    }
}
