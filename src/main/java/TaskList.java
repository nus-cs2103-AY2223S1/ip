import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String displayNumTasks() {
        String text = "\tNow you have " + this.taskList.size() +
                "tasks in the list.";
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

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public void printTasks() {

        if (this.taskList.size() == 0) {

            System.out.println("You currently have no tasks");
        } else {

            for (int i = 0; i < this.taskList.size(); i++) {

                System.out.println("\t" + (i + 1) + ". " + this.taskList.get(i));
            }
        }

    }

}