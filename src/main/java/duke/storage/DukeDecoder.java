package duke.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.logic.task.Deadline;
import duke.logic.task.Event;
import duke.logic.task.Task;
import duke.logic.task.ToDo;

/**
 * Class to initialize existing data.
 */
public class DukeDecoder {
    /**
     * Loads List of task from existing text file.
     * @return an ArrayList of Task
     */
    public ArrayList<Task> loadDataFromList() {
        ArrayList<Task> workList = new ArrayList<>();
        try {
            File directory = new File("./data");
            directory.mkdir();
            File file = new File(directory, "List.txt");
            file.createNewFile();
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] task = s.nextLine().split("\\|");
                assert task.length > 0 : "Task in history data cannot be empty";
                String typeOfTask = task[0];
                assert typeOfTask.length() > 0 : "Type of task cannot be empty";
                switch (typeOfTask) {
                case "T":
                    workList.add(new ToDo(task[2], task[1].equals("1")));
                    break;
                case "D":
                    assert task.length >= 3 : "Deadline should be followed by a date";
                    workList.add(new Deadline(task[2], task[1].equals("1"), task[3]));
                    break;
                case "E":
                    assert task.length >= 3 : "Event should be followed by a date";
                    workList.add(new Event(task[2], task[1].equals("1"), task[3]));
                    break;
                default:
                    // Fallthrough
                }
            }
            s.close();
            return workList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Cannot load file!");
        }
        return new ArrayList<Task>();
    }
}
