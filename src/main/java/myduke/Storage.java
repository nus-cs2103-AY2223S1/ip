package myduke;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class deals with reading tasks from the file to Duke, and also saving tasks from Duke to the file.
 */
public class Storage {
    //this is the physical file saving the items
    private File STORAGEFILE;

    private BufferedReader reader;
    private BufferedWriter writer;

    /**
     * Constructor for the class.
     * @param filepath file path of the storage file.
     */
    public Storage(String filepath) {
        STORAGEFILE = new File(filepath);
    }

    /**
     * This function reads the data from the file and stores it into the given taskList.
     * @param taskLists the taskList you want to store the data in.
     */
    public void loadFromFile(TaskList taskLists) {
        try {
            this.reader = new BufferedReader(new FileReader(STORAGEFILE));
            String currentLine;
            boolean status;
            while((currentLine = reader.readLine()) != null) {
                //type of task
                String type = currentLine.substring(0, 1);
                //whether the task is completed
                status = currentLine.substring(4, 5).equals("T");
                switch(type) {
                    case "T":
                        taskLists.add(new ToDo(currentLine.substring(8), status));
                        break;
                    case "D":
                        int divider = currentLine.substring(8).indexOf("|") + 8;
                        taskLists.add(new Deadline(currentLine.substring(8, divider - 1),
                                status, LocalDateTime.parse(currentLine.substring(divider + 2))));
                        break;
                    case "E":
                        int divider_2 = currentLine.substring(8).indexOf("|") + 8;
                        taskLists.add(new Event(currentLine.substring(8, divider_2 - 1),
                                status, currentLine.substring(divider_2 + 2)));
                        break;
                }
            }
            reader.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    /**
     * This functions stores the data from the given taskList into the file.
     * @param taskLists given taskList.
     */
    public void saveToFile(TaskList taskLists) {
        try {
            this.writer = new BufferedWriter(new FileWriter(STORAGEFILE));
            for (int i = 0; i < taskLists.getNumOfTask(); i++) {
                writer.write(taskLists.getTask(i).getDescription());
            }
            writer.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }
}
