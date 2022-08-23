package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the duke.storage.Storage class which stores data for duke.Duke.
 *
 * @author Leong Jia Hao Daniel
 */
public class Storage {

    private File currentFile;

    /**
     *
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.currentFile = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> temp = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File("data/duke.txt");
        try {
            if (data.exists()) {
                Scanner fileScanner = new Scanner(data);
                while (fileScanner.hasNext()) {
                    String string = fileScanner.nextLine();
                    char taskType = string.charAt(0);
                    try {
                        switch (taskType) {
                            case 'T':
                                ToDo todo = ToDo.parseFile(string);
                                temp.add(todo);
                                break;
                            case 'D':
                                Deadline deadline = Deadline.parseFile(string);
                                temp.add(deadline);
                                break;
                            case 'E':
                                Event event = Event.parseFile(string);
                                temp.add(event);
                                break;
                            default:
                                throw new DukeException("Error in storage!");
                        }
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                data.createNewFile();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return temp;
    }

    public void saveFile(TaskList data) {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter writer = new FileWriter("data/duke.txt", false);
            for (Task task : data.getTaskList()) {
                writer.write(task.toDataFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
