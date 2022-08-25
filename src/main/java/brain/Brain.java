import java.util.ArrayList;

public class Brain {
    String outputFileDirectory = "..\\data";
    String outputFileName = "duke.txt";
    public ArrayList<Task> taskList;

    public Brain(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Brain() {
        this.taskList = new ArrayList<Task>();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public String show() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Abrakadabraaa! Here's what's inside Tob Tob's Brain:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            result.add(String.format("%s.%s", i + 1, task.toString()));
        }

        return String.join("\n", result);
    }

    public String migrateBrainToTxt() {
        ArrayList<String> txtList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            txtList.add(task.toTxt());
        }

        return String.join("\n", txtList);
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }
}
