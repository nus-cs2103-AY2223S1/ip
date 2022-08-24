package cs2103t.ip.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;
    protected String folderPath;

    public Storage(String folderPath, String filePath) {
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    /**
     * Writes the provided text to the file specified by the file path.
     * @param filePath
     * @param textToAdd
     * @throws IOException
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the provided text to the file specified by the file path, adding to the existing
     * text without overwriting the whole file.
     * @param filePath
     * @param textToAppend
     * @throws IOException
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Loads saved data from the file to be displayed by Duke.
     * @return A list comprising the tasks entered previously to Duke by the user.
     * @throws DukeException
     */
    public ArrayList<Task> loadFileData() throws DukeException {
        ArrayList<Task> arr = new ArrayList<>();
        try {
            File folder = new File(folderPath);
            folder.mkdir();
            File file = new File(filePath);
            if (!file.createNewFile()) {
                Scanner scan = new Scanner(file);
                String input = "";
                while (scan.hasNext()){
                    input = scan.nextLine();
                    if (input.startsWith("T")) {
                        Todo todo = new Todo(input.substring(3));
                        if (input.substring(1).startsWith("X")) {
                            todo.makeDone();
                        }
                        arr.add(todo);
                    } else if (input.startsWith("E")) {
                        String[] time = input.split("/at");
                        Event event = new Event(time[0].substring(3), LocalDate.parse(time[1]));
                        if (input.substring(1).startsWith("X")) {
                            event.makeDone();
                        }
                        arr.add(event);
                    } else if (input.startsWith("D")) {
                        String[] dead = input.split("/by");
                        Deadlines deadlines = new Deadlines(dead[0].substring(3), LocalDate.parse(dead[1]));
                        if (input.substring(1).startsWith("X")) {
                            deadlines.makeDone();
                        }
                        arr.add(deadlines);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}
