package duke;

import duke.exceptions.DukeException;
import duke.exceptions.ImproperFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final String FILE_NAME = "storage.txt";
    private final File storageFile;
    private static final String DIVIDER = "[|]";

    public Storage() {
        this.storageFile = new File(FILE_NAME);
    }

    public void isCreated() {
        try {
            this.storageFile.createNewFile();
        } catch (IOException e) {
            System.out.println("COULD NOT CREATE NEW FILE\n"
                    + "REASON: "
                    + e.getMessage());
        }
    }

    public void load(TaskList taskList) throws FileNotFoundException, ImproperFormatException {
        assert taskList.size() == 0: "task list should be empty before loading existing data";
        Scanner reader = new Scanner(this.storageFile);
        while (reader.hasNext()) {
            String curr = reader.nextLine();
            // curr: T/D/E|0/1|task|date
            String[] currArr = curr.split(DIVIDER);
            // currArr: ["T/D/E", "0/1", "task", "date"]
            String currKeyword = currArr[0];
            String isDone = currArr[1];
            switch (currKeyword) {
                case ("T"):
                    if (isDone.equals("1")) {
                        Task toDo = new ToDo(currArr[2]);
                        toDo.toggleStatus();
                        taskList.addTask(toDo);
                        break;
                    }
                    taskList.addTask(new ToDo(currArr[2]));
                    break;
                case ("D"):
                    if (isDone.equals("1")) {
                        Task deadline = new Deadline(currArr[2], currArr[3]);
                        deadline.toggleStatus();
                        taskList.addTask(deadline);
                        break;
                    }
                    taskList.addTask(new Deadline(currArr[2], currArr[3]));
                    break;
                case ("E"):
                    if (isDone.equals("1")) {
                        Task event = new Event(currArr[2], currArr[3]);
                        event.toggleStatus();
                        taskList.addTask(event);
                        break;
                    }
                    taskList.addTask(new Event(currArr[2], currArr[3]));
                    break;
            }
        }
    }

    public void save(String task) throws DukeException {
        assert !task.isBlank(): "empty task should not be saved";
        try {
            FileWriter saver = new FileWriter(this.storageFile);
            saver.write(task);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
    public void save(TaskList taskList) {
        try {
            storageFile.delete();
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(taskList.generateSave());
            writer.close();
        } catch (IOException e) {
            System.out.println("CANNOT SAVE");
        }
    }
}
