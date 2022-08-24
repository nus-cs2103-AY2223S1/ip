package duke.storage;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.DukeException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(TaskList taskList) throws DukeException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        String filePath = "data/duke.txt";
        File data = new File(filePath);
        // Write to data
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            List<Task> listOfTasks = taskList.getTaskList();
            for (Task t : listOfTasks) {
                fileWriter.write(t.toFileDescription() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Hey! Are you in the wrong directory? You are currently at" +
                    e.getMessage());
        }
    }

    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        String filePath = "data/duke.txt";
        File data = new File(filePath);
        // Load the file data into corresponding ArrayList
        try {
            if (data.exists()) {
                Scanner scanner = new Scanner(data);
                while (scanner.hasNext()) {
                    String currentlyAt = scanner.nextLine();
                    char first = currentlyAt.charAt(0);
                    Task task;
                    switch(first) {
                    case 'T':
                        task = Todo.fromFileDescription(currentlyAt);
                        break;
                    case 'D':
                        task = Deadline.fromFileDescription(currentlyAt);
                        break;
                    case 'E':
                        task = Event.fromFileDescription(currentlyAt);
                        break;
                    default:
                        throw new DukeException("What!? How did this happen... I'm pretty sure you" +
                                "have an itchy hand and modified the duke.txt file!!!");
                    }
                    list.add(task);
                }
            } else {
                data.createNewFile();
                throw new DukeException("Welcome! This is your first time using Duke right?");
            }
        } catch (IOException e) {
            throw new DukeException("Are you a hacker? How on earth did you get to this stage!?");
        }
        return list;
    }
}
