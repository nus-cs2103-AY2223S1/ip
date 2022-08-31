package tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveData {

    private List<Task> taskList;

    public SaveData() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList1) {
        taskList = taskList1;
    }

    public void loadFromFile(File f) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            taskList.clear();
            reader.lines().forEach((s) -> {
//                System.out.println(s);
                taskList.add(Task.fromDataString(s));
            });
        } catch (IOException e) {
            System.out.println("Error while loading from file: " + e);
        }
    }

    public void saveToFile(File f) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            for (Task task : taskList) {
                writer.write(task.toDataString());
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e);
        }

    }

    public static void main(String[] args) {
        SaveData data = new SaveData();
        List<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Deadline("Deadline task", "September"));
        newTaskList.add(new Todo("Todo task"));
        newTaskList.add(new Event("Event task", "October"));
        data.setTaskList(newTaskList);
        data.saveToFile(new File("savedata.txt"));
        data.loadFromFile(new File("savedata.txt"));
    }

    /*
    File specification:
        [T] | 1 | Task Name | Time
     */

}
