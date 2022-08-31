package duke.taskmanager;

import duke.taskmanager.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static final String FILEPATH = "tasklist.txt";
    private final List<Task> taskList;
    private static final String DATE_FORMAT = "dd/MM/yyyy,HHmm";
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public String getDateFormat() {
        return DATE_FORMAT;
    }

    private Task processFormattedString(String formattedString) throws Exception {
        String[] arguments = formattedString.split("<>");
        String taskType = arguments[0];
        boolean status = (arguments[1].equals("1") ? true : false);
        String taskName = arguments[2];

        switch (taskType) {
        case "T":
            return new ToDoTask(taskName, status);
        case "D":
            return new DeadlineTask(taskName, arguments[3], status, DATE_FORMAT);
        case "E":
            return new EventTask(taskName, arguments[3], status, DATE_FORMAT);
        default:
            return new EmptyTask();
        }
    }
    public String list() {
        if (taskList.size() == 0) {
            return "\tYou have no tasks in your list.\n";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\tI have your list of tasks displayed below:\n");
            for (int i = 0; i < this.taskList.size(); i++) {
                stringBuilder.append("\t" + (i + 1) + ") " + taskList.get(i) + "\n");
            }
            return stringBuilder.toString();
        }
    }
    public String addTask(Task task) {
        this.taskList.add(task);
        return ("\t> Added: " + task.getTaskName() + "\n");
    }

    public String mark(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (this.taskList.get(itemNumber - 1).getStatus()) {
                return "\tThe task is already marked you dummy.\n";
            } else {
                this.taskList.get(itemNumber - 1).setStatus(true);
                return "\tI've marked this task as done. Good Job!\n";
            }
        } else {
            return "\tThere is no such task!!\n";
        }
    }

    public String unmark(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (!(this.taskList.get(itemNumber - 1).getStatus())) {
                return "\tThe task is still not done you idiot.\n";
            } else {
                this.taskList.get(itemNumber - 1).setStatus(false);
                return "\tThe task has been unmarked.\n";
            }
        } else {
            return "\tThere is no such task!!\n";
        }
    }

    public String delete(int itemNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            stringBuilder.append("The following item has been removed.\n");
            stringBuilder.append(this.taskList.remove(itemNumber - 1).toString() + "\n");
            stringBuilder.append("You have " + (this.taskList.size()) + " item(s) remaining.\n");
        } else {
            return "\tThere is no such task!!\n";
        }
        return stringBuilder.toString();
    }

    /**
     * Finds and returns a list of task containing the keyword provided by the user.
     *
     * @param keyword string of the keyword to match in the task list
     * @return a response message listing out the task containing the keyword
     */
    public String find(String keyword) {
        List<Integer> keywordList = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTaskName().contains(keyword)) {
                keywordList.add(i);
            }
        }
        if (keywordList.size() == 0) {
            return "\tYou have no tasks in your list with the keyword \"" + keyword + "\".\n";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\tI have the matching tasks displayed below:\n");
            for (Integer index : keywordList) {
                stringBuilder.append("\t").append(index + 1).append(") ").append(this.taskList.get(index)).append("\n");
            }
            return stringBuilder.toString();
        }
    }

    public void save() {
        try {
            File file = new File(FILEPATH);
            if (!(file.exists())) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : this.taskList) {
                fileWriter.write(task.getFormattedString());
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void load() {
        try {
            File file = new File(FILEPATH);
            if (!(file.exists())) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            try {
                while (fileScanner.hasNextLine()) {
                    Task newTask = processFormattedString(fileScanner.nextLine());
                    if (!(newTask.isEmpty())) {
                        taskList.add(newTask);
                    }
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
}