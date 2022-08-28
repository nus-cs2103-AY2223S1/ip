package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> userTasks;

    public TaskList() {
        userTasks = new ArrayList<Task>();
    }

    public int getSize() {
        return userTasks.size();
    }

    public String getLastTask() {
        return userTasks.get(userTasks.size() - 1).returnDescription();
    }
    public void appendToDo(String inputString) {
        userTasks.add(new ToDo(inputString));
    }

    public void appendToDoFromFile(String taskDescription, boolean isCompleted) {
        userTasks.add(new ToDo(taskDescription, isCompleted));
    }

    public void appendDeadline(String taskDescription, String date) {
        date = date.replace("by ","");
        userTasks.add(new Deadline(taskDescription, date));
    }

    public void appendDeadlineFromFile(String taskDescription, String date, boolean isCompleted) {
        userTasks.add(new Deadline(taskDescription, date, isCompleted));
    }
    public void appendEvent(String taskDescription, String dateTime) {
        dateTime = dateTime.replace("at ","");
        userTasks.add(new Event(taskDescription, dateTime));
    }

    public void appendEventFromFile(String taskDescription, String dateTime, boolean isCompleted) {
        userTasks.add(new Event(taskDescription, dateTime, isCompleted));
    }

    public String removeTask(String number) {
        int index = Integer.parseInt(number) - 1;
        Task deletedTask = userTasks.remove(index);
        String taskMessage = deletedTask.returnDescription();
        return taskMessage;
    }

    public String markSpecificTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        Task currentTask = userTasks.get(index);
        currentTask.markTask();
        String content = currentTask.returnDescription();
        return content;
    }

    public String unmarkSpecificTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        Task currentTask = userTasks.get(index);
        currentTask.unmarkTask();
        String content = currentTask.returnDescription();
        return content;
    }

    public String listOfTaskForDisplay() {
        String content = "";
        int index = userTasks.size();
        for (int i = 0; i < index; i++) {
            content += String.valueOf(i + 1) + ". " + userTasks.get(i).returnDescription();
            if(i != index - 1) {
                content += "\n";
            }
        }
        return content;
    }

    public String listOfMatchedTasks(String substring) {
        String content = "";
        int index = userTasks.size();
        for (int i = 0; i < index; i++) {
            if (userTasks.get(i).getTaskDescription().contains(substring)) {
                content += userTasks.get(i).returnDescription();
                content += "\n";
            }
        }
        return content;
    }

    public String listOfTasksForSaving() {
        String content = "";
        for (Task task: userTasks) {
            content += task.toWriteFile() + System.lineSeparator();
        }
        return content;
    }

}
