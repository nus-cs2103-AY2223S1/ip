import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * A Storage class that stores the task in task list.
 *
 * CS2103T iP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Storage {
    /** Save location of task list */
    File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Saves Tasks into save file.
     *
     * @param tasks The List of Task to write from.
     */
    public void save(TaskList tasks) {
        // Create Directory or File if it does not exist
        try {
            if (!file.exists()) {
                File directory = new File(file.getParent());

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // Write into File
        try {
            String line;
            String type;
            String done;
            String desc;
            String date;
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks.getTaskList()) {
                type = task.getType();
                desc = task.getDescription();
                done = task.isDone ? "1" : "0";
                date = task.getDate();

                line = type + " > " + desc + " > " + done + " > " + date;
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line = "";
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Hmm... Error while saving Task to file");
        }
    }

    /**
     * Loads Tasks from save file.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("Save file does not exist");
            return tasks;
        }

        try {
            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {

                String[] arr = line.split(" > ");
                String type = arr[0];
                switch (type) {
                    case "T" :
                        tasks.add(new ToDo(arr[1], arr[2]));
                        break;
                    case "E" :
                        tasks.add(new Event(arr[1], arr[2], arr[3]));
                        break;
                    case "D" :
                        tasks.add(new Deadline(arr[1], arr[2], arr[3]));
                        break;
                }

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException | DukeInvalidTimeException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }
}
