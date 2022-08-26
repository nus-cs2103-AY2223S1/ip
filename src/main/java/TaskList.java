import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
//        try {
//            loadTasksFromFile();
//        }catch (Exception e) {
//            System.out.println(e);
//        }
    }

    public void addTask(Task t)  {
        this.taskList.add(t);
        try {
            FileDataHandler.save(this.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        String reply = "Got it. I've added this task:\n" +
                        t + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        System.out.println(reply);
    }

    public void addTaskWithoutPrinting(Task t)  {
        this.taskList.add(t);
        try {
            FileDataHandler.save(this.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }





    public Task getTask(int taskNo) {
        return taskList.get(taskNo - 1);
    }

    public void markTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void unMarkTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.unMark();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void deleteTask(int taskNo) {
        Task t = this.getTask(taskNo);
        this.taskList.remove(taskNo - 1);
        try {
            FileDataHandler.save(this.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        String reply = "Noted. I've removed this task:\n" +
                        t + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        System.out.println(reply);
    }


//    public void loadTasksFromFile() throws IOException {
//            Scanner s = FileDataHandler.load();
//            while (s.hasNext()) {
//                char taskType = s.nextLine().charAt(1);
//                System.out.println(taskType);
//            }
//    }

    public void printList() {
        System.out.println("Here are the tasks in your list:\n");
        int ListLength = taskList.size();
        for (int i = 0; i < ListLength; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }


}
