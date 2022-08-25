package Duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading and saving tasks in the file
 */
public class Storage {

    /**
     * Reads lines from a file and stores them in a list.
     * @param filescanner Scanner to read from the file.
     * @return List containing all the tasks from a file.
     */
    public List<String> readTasks(Scanner filescanner) {
        List<String> lst = new ArrayList<String>(0);
        while (filescanner.hasNextLine()) {
            String task = filescanner.nextLine();
            lst.add(task);
        }
        return lst;
    }

    /**
     * Adds tasks to file.
     * @param pathName Pathname of file
     * @param lst List of tasks to add to file.
     * @throws IOException If there is an error writing to file.
     */
    public void addTasks(String pathName,List<String> lst ) throws IOException {
        for (int i=0; i < lst.size(); i++) {
            writeToFile(pathName, lst.get(i));
        }
    }

    /**
     * Overwrite the current file.
     * @param pathName Pathname of file.
     * @param lst1 First list to write to file.
     * @param lst2 Second list to write to file.
     * @throws IOException If there is an error writing to file.
     */
    public void replaceTasks(String pathName, List<String> lst1, List<String> lst2) throws IOException {
        System.out.println("Code has been called to replace tasks");
        overwriteFile(pathName,lst1.get(0));
        for (int i=1; i < lst1.size(); i++) {
            writeToFile(pathName, lst1.get(i));
        }
        for (int i=0; i < lst2.size(); i++) {
            writeToFile(pathName, lst2.get(i));
        }
    }

    /**
     * Writes to file.
     * @param filePath Pathname of file.
     * @param textToAdd Text to write to file.
     * @throws IOException If there is an error writing to file.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    /**
     * Overwrites existing file.
     * @param filePath Pathname of file.
     * @param textToAdd Text to write to file.
     * @throws IOException If there is an error writing to file.
     */
    public void overwriteFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,false);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

}
