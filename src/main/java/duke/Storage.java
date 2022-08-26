package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulation of reading from and writing into file.
 *
 * @author   Sun Ruoxin
 * @version  %I%, %G%
 */
public class Storage {
    /**
     * The file being read from and write to.
     */
    protected File file;

    /**
     * Class constructor.
     *
     * @param filePath  the path of the file
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads from the file containing existing records of Tasks.
     *
     * @param ui  the UI handling interactions with the user
     * @return    the list of existing Tasks
     */
    public ArrayList<Task> load(Ui ui) {
        initialiseFile(ui);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task = Parser.toTask(scanner.nextLine());
                taskList.add(task);
            }
            scanner.close();
        } catch (IOException exception) {
            ui.say(exception.getMessage(), true, true);
        }

        return taskList;
    }

    /**
     * Creates the directory and the file if not already exist.
     *
     * @param ui  the UI handling interactions with the user
     */
    public void initialiseFile(Ui ui) {
        File directory = new File(this.file.getParent());
        if (!directory.exists()) {
            System.out.println("directory created");
            directory.mkdir();
        }

        try {
            if (!file.exists()) {
                System.out.println("file created");
                file.createNewFile();
            }
        } catch (IOException exception) {
            ui.say(exception.getMessage(), true, true);
        }
    }

    /**
     * Writes the current list of Tasks to the file.
     *
     * @param list  the current list of Tasks
     * @param ui    the UI handling interactions with the user
     */
    public void writeFile(TaskList list, Ui ui) {
        initialiseFile(ui);

        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                if (task instanceof Todo) {
                    writer.write(task.getType() + " | " + task.getStatusInt() + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    writer.write(task.getType() + " | " + task.getStatusInt() + " | " + task.getDescription()
                            + " | " + deadline.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
                }
            }
            writer.close();
        } catch (IOException exception) {
            ui.say(exception.getMessage(), true, true);
        }
    }
}
