package bob;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents Storage object, where tasks are saved and retrieved from when Bob restarts
 */
public class Storage {

    private File saveFile;

    /**
     * Constructor object for Storage,
     * creates a path and file if it does not exist, then initializes that as the storage location,
     * else initializes currently existing path
     */
    public Storage() {
        String directoryPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "lists";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
            File taskList = new File(directoryPath, "tasklist.txt");
            try {
                FileWriter fileWriter = new FileWriter(taskList);
                fileWriter.close();
                this.saveFile = taskList;
            } catch (java.io.IOException e) {
                System.out.println("error! not able to create file!");
            }
        } else {
            String FilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "lists" + System.getProperty("file.separator") + "tasklist.txt";
            File saveFile = new File(FilePath);
            if (!saveFile.exists()) {
                File taskList = new File(directoryPath, "tasklist.txt");
                this.saveFile = taskList;
                try {
                    FileWriter fileWriter = new FileWriter(taskList);
                    fileWriter.close();
                } catch (java.io.IOException e) {
                    System.out.println("error! not able to create file!");
                }
            } else {
                this.saveFile = saveFile;
            }
        }
    }

    /**
     * Reads tasks from storage file and returns task list
     *
     * @return TaskList containing all tasks in storage file
     */
    public TaskList read() {
        TaskList tasks = new TaskList();
        try {
            Scanner reader = new Scanner(this.saveFile);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                String[] temp = task.split("\\|");
                String taskLabel = temp[0].substring(0, 1);
                switch (taskLabel) {
                    case "T":
                        String todoTaskName = temp[2].substring(1);
                        ToDo todo = new ToDo(todoTaskName);
                        if (temp[1].substring(1, 2).equals("1")) {
                            todo.toMark(true);
                        }
                        tasks.addTask(todo);
                        break;
                    case "D":
                        String deadlineTaskName = temp[2].substring(1);
                        String[] deadlineDetails = temp[3].substring(1).split(" ");
                        String deadlineDate = deadlineDetails[0];
                        Deadline deadline = new Deadline(deadlineTaskName, LocalDate.parse(deadlineDate));
                        if (temp[1].substring(1, 2).equals("1")) {
                            deadline.toMark(true);
                        }
                        tasks.addTask(deadline);
                        break;
                    case "E":
                        String eventTaskName = temp[2].substring(1);
                        String[] eventDetails = temp[3].substring(1).split(" ");
                        String eventDate = eventDetails[0];
                        Event event = new Event(eventTaskName, LocalDate.parse(eventDate));
                        if (temp[1].substring(1, 2).equals("1")) {
                            event.toMark(true);
                        }
                        tasks.addTask(event);
                        break;
                }
            }
            return tasks;
        } catch (java.io.FileNotFoundException e) {
            System.out.println("error! can't find file!");
        }
        return tasks;
    }

    /**
     * Saves current TaskList to storage file
     *
     * @param tasks list of tasks to be saved
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.saveFile);
            String list = "";
            for (int i = 1; i < tasks.getLength() + 1; i++) {
                list = list + tasks.getTask(i).toSave() + "\n";
            }
            fileWriter.write(list);
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("error! not able to save file :(");
        }
    }
}
