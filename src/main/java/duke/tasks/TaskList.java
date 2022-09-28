package duke.tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String displayNumTasks() {
        String text = "Now you have " + this.taskList.size() +
                " tasks in the list.";
        return text;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void markTask(int index) {
        this.taskList.get(index).setDone(true);
    }

    public void unmarkTask(int index) {
        this.taskList.get(index).setDone(false);
    }

    public Task deleteTask(int index) {
        return this.taskList.remove(index);
    }

    public String taskListString() {

        String text = "";

        if (this.taskList.size() == 0) {

            return "You currently have no tasks";
        } else {

            for (int i = 0; i < this.taskList.size(); i++) {

                text += "\t" + (i + 1) + ". " +
                        this.taskList.get(i).toString() + "\n";
            }
        }
        return text;
    }

    public String taskListFileString() {

        String text = "";
        for (Task task : this.taskList) {
            text += task.fileString();
        }
        return text;
    }

    public String getTaskString(int index) {
        return this.taskList.get(index).toString();
    }

    public ArrayList<Task> findTasks(String search) {
        ArrayList searchMatchList = new ArrayList<Task>();
        for(Task task : taskList) {
            if (task.getDescription().contains(search)) {
                searchMatchList.add(task);
            }
        }
        return searchMatchList;
    }

}