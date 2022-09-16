package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that is used to store the data in a file.
 *
 * @author Safwan A0235287X
 */
public class Storage {

    private static String filePath;
    private File file;
    private static ArrayList<Task> inputList;

    /**
     * Constructor to create Storage object.
     *
     * @param filePath Location of the file that stores the data as a result of running the main class.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.inputList = new ArrayList<>();
        this.file = new File(filePath);
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        } if (!this.file.exists()) {
            this.file.createNewFile();
        }
    }

    /**
     * Method that takes the data stored in the list and inputs it in a text file.
     * @param textToAdd The input list of data.
     * @throws IOException
     */
    public static void writeToFile(ArrayList<Task> textToAdd) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File does not exist.");
        }

        FileWriter fwriter = new FileWriter(filePath);
        String output = "";
        for (Task item : textToAdd) {
            output += (textToAdd.indexOf(item) + 1) + "." + item  + "\n";
        }

        fwriter.write(output);
        fwriter.close();
    }

    /**
     * Method to load the contents of the file in a list.
     * @return A list of all the tasks in the file, in the type of an ArrayList.
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {

        try {
            Scanner scanner = new Scanner(file);
            if (file.length() == 0) {
                throw new DukeException ("File is Empty!");

            } else {
                while (scanner.hasNextLine()) {
                    String task = scanner.nextLine();
                    String type = String.valueOf(task.charAt(1));
                    assert type.equals("T") || type.equals("E") || type.equals("D");

                    if (type.equals("T")) {
                        String currTask = task.substring(9);
                        Task td = new Todo(" " + currTask);
                        inputList.add(td);
                    } else if (type.equals("E")) {
                        int splitter = task.indexOf(" (at: ");
                        String currTask = task.substring(9, splitter);
                        String time = task.substring(splitter + 6, task.indexOf(")"));
                        Task ev = new Event(" " + currTask, time);
                        inputList.add(ev);
                    } else if (type.equals("D")) {
                        int splitter = task.indexOf(" (by: ");
                        String currTask = task.substring(9, splitter);
                        String time = task.substring(splitter + 6, task.indexOf(")"));
                        Task dl = new Deadline(" " + currTask, time);
                        inputList.add(dl);
                    } else {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
        } catch (DukeException e) {
            File file = new File(filePath);
            throw new DukeException ("File is empty!");
        }
        return inputList;
    }
}
