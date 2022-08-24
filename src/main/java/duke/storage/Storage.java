package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.data.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Storage {

    public static final String PATH = "./data/";
    protected File file;

    public Storage() {
        this.file = new File(PATH + "duke.txt");
        setup();
    }

    public void setup() {
        File directory = new File(PATH);
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }

            file = new File(PATH, "duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : taskList.getAllTasks()) {
                fileWriter.write(task.savedString() + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public TaskList load() {
        TaskList taskList = new TaskList();

        try {
            Scanner fileScanner = new Scanner(this.file);

            while (fileScanner.hasNext()) {
                String[] taskString = fileScanner.nextLine().split(",");
                String taskType = taskString[0];

                switch (taskType) {
                    case "T":
                        Task todo = new ToDo(taskString[2], taskString[1].equals("Y"));
                        taskList.add(todo);
                        break;
                    case "E":
                        Task event = new Event(taskString[2], taskString[3], taskString[1].equals("Y"));
                        taskList.add(event);
                        break;
                    case "D":
                        Task deadline = new Deadline(taskString[2], taskString[3], taskString[1].equals("Y"));
                        taskList.add(deadline);
                        break;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            return taskList;
        }
    }


}
