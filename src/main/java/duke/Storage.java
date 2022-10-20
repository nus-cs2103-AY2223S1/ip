package duke;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class for file I/O
 */
public class Storage {
    static final String FILE_PATH = "data/duke.txt";

    /**
     * Reads tasks data from file at {@code FILE_PATH}
     *
     * @return list of Tasks, empty if could not read from file
     */
    public List<Task> readDataFile() {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                throw new DukeException(String.format("file %s not found", FILE_PATH));
            }

            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String data = s.nextLine();
                String[] taskInfo = data.split(" \\| ");

                Task task;
                switch (taskInfo[0]) {
                case "T":
                    task = new Todo(taskInfo[2]);
                    break;
                case "E":
                    LocalDate date = LocalDate.parse(taskInfo[3]);
                    task = new Event(taskInfo[2], date);
                    break;
                case "D":
                    date = LocalDate.parse(taskInfo[3]);
                    task = new Deadline(taskInfo[2], date);
                    break;
                default:
                    throw new DukeException(String.format("corrupt file: %s", FILE_PATH));
                }
                if (Integer.parseInt(taskInfo[1]) == 1) {
                    task.mark();
                }
                tasks.add(task);
            }
            s.close();

            System.out.printf("Loaded tasks from %s\n", FILE_PATH);
        } catch (Exception e) {
            System.out.println("Error: could not read from file: ");
            System.out.println(e);
        }

        return tasks;
    }

    /**
     * Writes tasks to file at {@code FILE_PATH}
     */
    public void syncTasksToFile(TaskList taskList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println(String.format("File %s not found, creating...", FILE_PATH));
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            List<Task> tasks = taskList.getTasks();
            StringBuilder taskStrings = new StringBuilder();
            for (Task task : tasks) {
                String taskString = task.toFileString();
                taskStrings.append(String.format("%s\n", taskString));
            }

            writer.write(taskStrings.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: could not write to file: ");
            System.out.println(e);
        }
    }
}
