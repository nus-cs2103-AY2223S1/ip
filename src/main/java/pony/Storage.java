package pony;

import pony.task.*;

import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage handles all the interaction with the memory.
 */
public class Storage {
    private Path taskListPath = null;
    private String fileName;

    /**
     * Constructor for Storage.
     * @param fileName Description of the path to the memory file.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        this.taskListPath = Paths.get(fileName);
    }

    /**
     * Loads the list of tasks loaded from memory into the program.
     *
     * @return An ArrayList of Tasks.
     */
    public ArrayList<Task> loadTaskList() {
        // Check if file exist
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("does the file exist?: " + Files.exists(taskListPath));
        if (Files.exists(taskListPath)) {
            try {
                List<String> taskList = Files.readAllLines(taskListPath);
                for (int i = 0; i < taskList.size(); i++) {
                    String taskDetail = taskList.get(i);
                    processTaskDetail(taskDetail, tasks);
                }
            } catch (IOException | SecurityException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                System.out.println("");
                Files.createFile(taskListPath);
                System.out.println("I created a file!");
            } catch (IOException | SecurityException e) {
                System.out.println("File creating error");
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Process task details and turn it into a task.
     *
     * @param taskDetail Task details.
     * @param tasks An ArrayList to store all the tasks.
     */
    private void processTaskDetail(String taskDetail, ArrayList<Task> tasks) {
        String[] strArr = taskDetail.split(" \\Q|\\E ", 4);
        Task newTask = null;
        switch (strArr[0]) {
            case "T":
                newTask = new ToDo(strArr[2]);
                tasks.add(newTask);
                break;
            case "D":
                newTask = new Deadline(strArr[2], strArr[3]);
                tasks.add(newTask);
                break;
            case "E":
                newTask = new Event(strArr[2], strArr[3]);
                tasks.add(newTask);
                break;
        }
        if (strArr[1].equals("1")) {
            newTask.markAsDone();
        }
    }

    /**
     * Updates the memory with an updated task list.
     * @param tasks Task list with updated details.
     */
    public void updateDisk(TaskList tasks) {
        String data = "";
        ArrayList<Task> taskArr = tasks.getAllTasks();
        for (Task task : taskArr) {
            data += task.saveToDisk();
        }
        try {
            FileWriter fw = new FileWriter(this.fileName, false);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to write to file.");
        }
    }
}

