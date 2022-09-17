package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * A Class that deals with the loading tasks from the file and saving tasks in the file.
 *
 * @author Denzel Tan
 */
public class Storage {
    private final String filePath;

    /**
     * The constructor for the Storage class.
     *
     * @param filePath path of the file to be used
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads the current text file, updates the ArrayList.
     */
    public ArrayList<String> loadFile() throws FileNotFoundException {
        ArrayList<String> strList = new ArrayList<>();
        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNext()) {
                strList.add(myReader.nextLine());
            }
            myReader.close();
            return strList;

        } catch (IOException e) {
            System.out.println("Created a new text file for storage:  duke.txt");
        }
        return strList;
    }


    /**
     * Writes the following tasks in the taskList to the text file.
     *
     * @param tasks the taskList to write in the text file
     */
    public static void writeToFile(TaskList tasks) throws IOException {
        Writer newTextLine = new BufferedWriter(new FileWriter("duke.txt", false));
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            if (currTask instanceof Todo) {
                if (!Objects.equals(currTask.getTag(), "")) {
                    newTextLine.append("T | ").append(currTask.getStatusIcon()).append(" |")
                            .append(currTask.description).append("#").append(currTask.getTag()).append("\n");
                } else {
                    newTextLine.append("T | ").append(currTask.getStatusIcon()).append(" |")
                            .append(currTask.description).append("\n");
                }
            } else if (currTask instanceof Deadline) {
                Deadline currDeadline = (Deadline) currTask;
                if (!Objects.equals(currTask.getTag(), "")) {
                    newTextLine.append("D | ").append(currDeadline.getStatusIcon()).append(" |")
                            .append(currDeadline.description).append("|")
                            .append(currDeadline.date.toString()).append("#").append(currTask.getTag()).append("\n");
                } else {
                    newTextLine.append("D | ").append(currDeadline.getStatusIcon()).append(" |")
                            .append(currDeadline.description).append("|")
                            .append(currDeadline.date.toString()).append("\n");
                }
            } else if (currTask instanceof Event) {
                Event currEvent = (Event) currTask;
                if (!Objects.equals(currTask.getTag(), "")) {
                    newTextLine.append("E | ").append(currEvent.getStatusIcon()).append(" |")
                            .append(currEvent.description).append("|").append(currEvent.at)
                            .append("#").append(currTask.getTag()).append("\n");
                } else {
                    newTextLine.append("E | ").append(currEvent.getStatusIcon()).append(" |")
                            .append(currEvent.description).append("|").append(currEvent.at).append("\n");
                }
            }
        }
        newTextLine.close();
    }
}
