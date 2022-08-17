package duke.tasks;

import duke.Duke;
import duke.DukeException;
import utils.Constants;
import utils.DukeUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskManager {

    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    private Task getTask(int index) {
        return tasks.get(index);
    }

    public void printTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("  %d.%s\n\t", i + 1, getTask(i)));
        }
        DukeUtils.printMessages(Constants.MSG_TASK_LIST, sb.toString().stripTrailing());
    }

    public void addTask(String inputCommand, String inputDesc) {
        Task task = TaskFactory.createTask(TaskType.valueOf(inputCommand.toUpperCase()), inputDesc);
        tasks.add(task);
        DukeUtils.printMessages(
                Constants.MSG_TASK_ADDED,
                "  " + task,
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

    public void deleteTask(String inputDesc) throws DukeException {
        int index = checkIndex(inputDesc);
        Task task = tasks.get(index);
        tasks.remove(index);
        DukeUtils.printMessages(
                Constants.MSG_TASK_DELETED,
                "  " + task,
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

    public void markTask(String inputDesc) throws DukeException {
        updateTaskStatus(inputDesc, true);
    }

    public void unmarkTask(String inputDesc) throws DukeException {
        updateTaskStatus(inputDesc, false);
    }

    public void saveTasksToDisk() {
        BufferedWriter writer;
        try {
            new PrintWriter(Duke.DATA_RELATIVE_URL).close();
            writer = new BufferedWriter(new FileWriter(new File(Duke.DATA_RELATIVE_URL)));
            for (Task t : tasks) {
                writer.write(t.savedString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readTasksFromDisk() {
        BufferedReader reader;
        try {
            reader = new BufferedReader((new FileReader(Duke.DATA_RELATIVE_URL)));
            String line = reader.readLine();

            String[] taskArray;
            String taskType, description, time;
            boolean isDone;

            while (line != null) {
                taskArray = Arrays.stream(line.split("\\|")).map(String::trim).toArray(String[]::new);
                taskType = taskArray[0];
                isDone = taskArray[1].equals("0");
                description = taskArray[2];
                Task task = null;

                switch (taskType) {
                case "T":
                    task = TaskFactory.createTask(TaskType.TODO, description);
                    break;
                case "D":
                    task = TaskFactory.createTask(TaskType.DEADLINE, description + " /by " + taskArray[3]);
                    break;
                case "E":
                    task = TaskFactory.createTask(TaskType.EVENT, description + " /at " + taskArray[3]);
                    break;
                }

                if (task != null) {
                    task.setDone(!isDone);
                    tasks.add(task);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTaskStatus(String inputDesc, boolean isDone) throws DukeException {
        int index = checkIndex(inputDesc);
        tasks.get(index).setDone(isDone);
        DukeUtils.printMessages(
                String.format(Constants.MSG_TASK_UPDATE_STATUS, isDone ? "done" : "not done"),
                "  " + getTask(index));
    }

    private int checkIndex(String inputDesc) throws DukeException {
        int taskNumber = Integer.parseInt(inputDesc);
        int actualListIndex = taskNumber - 1;
        if (actualListIndex >= tasks.size() || actualListIndex < 0) {
            throw new DukeException(Constants.ERROR_INVALID_NUMBER);
        }
        return actualListIndex;
    }

}
