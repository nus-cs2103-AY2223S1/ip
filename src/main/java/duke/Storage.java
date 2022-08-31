package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * The Storage class that stores the tasks in TaskList.
 *
 * CS2103T iP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Storage {
    /** Save location of TaskList */
    private File file;
    private String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath Location of save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
    }

    /**
     * Saves tasks into save file.
     *
     * @param taskList The List of Task to write from.
     */
    public void save(TaskList taskList) {
        // Create Directory or File if it does not exist
        try {
            if (!this.file.exists()) {
                File directory = new File(this.file.getParent());

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // Write into File
        try {
            FileWriter fileWriter = new FileWriter(this.file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            List<Task> tasks = taskList.getTasks();

            for (Task task : tasks) {
                String data = task.stringify();
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Hmm... Error while saving your data to file");
        }
    }

    /**
     * Loads tasks from save file.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        if (!this.file.exists()) {
            System.out.println("Save file does not exist");
            return tasks;
        }

        try {
            FileReader fileReader = new FileReader(this.file.getAbsoluteFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {

                String[] arr = line.split(" \\| ");
                String type = arr[0];
                switch (type) {
                case "T":
                    tasks.add(new ToDo(arr[1], arr[2].equals("1")));
                    break;
                case "E":
                    tasks.add(new Event(arr[1], arr[2].equals("1"), LocalDateTime.parse(arr[3])));
                    break;
                case "D":
                    tasks.add(new Deadline(arr[1], arr[2].equals("1"), LocalDateTime.parse(arr[3])));
                    break;
                default:
                    // Does Nothing
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();

            return tasks;
        } catch (IOException | DateTimeParseException e) {
            throw new DukeException("Invalid String in load file.");
        }
    }
}
