package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

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
            if (myFile.createNewFile()) {
                Ui.showLoadingError(myFile.getName());
            } else {
                Ui.showExistingFile(myFile.getName());
            }

            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNext()) {
                strList.add(myReader.nextLine());
            }
            myReader.close();
            return strList;

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return strList;
    }


    /**
     * Writes the following tasks in the taskList to the text file.
     *
     * @param tasks the taskList to write in the text file
     */
    public static void writeToFile(TaskList tasks) throws IOException {
        Writer newTextLine = new BufferedWriter(new FileWriter("./src/main/java/duke.txt", false));
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            if (currTask instanceof Todo) {
                newTextLine.append("T | ").append(currTask.getStatusIcon()).append(" |")
                        .append(currTask.description).append("\n");
            } else if (currTask instanceof Deadline) {
                Deadline currDeadline = (Deadline) currTask;
                newTextLine.append("D | ").append(currDeadline.getStatusIcon()).append(" |")
                        .append(currDeadline.description).append("|").append(currDeadline.date.toString()).append("\n");
            } else if (currTask instanceof Event) {
                Event currEvent = (Event) currTask;
                newTextLine.append("E | ").append(currEvent.getStatusIcon()).append(" |")
                        .append(currEvent.description).append("|").append(currEvent.at).append("\n");
            }
        }
        newTextLine.close();
    }
}
