package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that creates Storage object to do file manipulation
 * @author amresh A0235398R
 */
public class Storage {

    protected String filePath;
    protected ArrayList<Task> storeLists = new ArrayList<>();

    /**
     * Constructor that creates Storage object
     *
     * @param filePath Location where file is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads file at the beginning of the program
     *
     * @return ArrayList<Task> that will return when method runs
     */
    public ArrayList<Task> loadFile() {
        try {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            File taskFile = path.toFile();
            if (!taskFile.createNewFile()) {
                Scanner sc = new Scanner(new FileReader(taskFile));
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    renderStringAsTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return storeLists;
    }

    /**
     * Method that parses strings and converts them to Tasks to be added to storeList
     *
     * @param taskStr String that will be passed into method to convert
     */
    private void renderStringAsTask(String taskStr) {
        String taskType = String.valueOf(taskStr.charAt(3));
        assert taskType.equals("T") || taskType.equals("E") || taskType.equals("D");

        String taskDescription = taskStr.split("] ", 2)[1];
        if (taskType.equals("T")) {
            Todo t = new Todo(taskDescription);
            storeLists.add(t);
        } else if (taskType.equals("D")) {
            String duration = taskDescription.split("y: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Deadline d = new Deadline(taskDescription, duration);
            storeLists.add(d);
        } else {
            String duration = taskDescription.split("t: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Event e = new Event(taskDescription, duration);
            storeLists.add(e);
        }
    }

    /**
     * Writes any additions, removals or changes to tasks to file
     *
     * @param textToAlter text that needs to be added or removed or changed
     */
    public void writeToFile(ArrayList<Task> textToAlter) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("File does not exist.");
            }

            FileWriter fw = new FileWriter(filePath);
            String output = "";
            for (Task item : textToAlter) {
                output += (textToAlter.indexOf(item) + 1) + "." + item  + "\n";
            }

            fw.write(output);
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
