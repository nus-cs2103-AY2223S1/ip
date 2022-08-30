package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import duke.storage.Storage;

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
            Storage.save(this.taskList);
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
            Storage.save(this.taskList);
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
        try {
            Storage.save(this.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void unMarkTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.unMark();
        try {
            Storage.save(this.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void deleteTask(int taskNo) {
        Task t = this.getTask(taskNo);
        this.taskList.remove(taskNo - 1);
        try {
            Storage.save(this.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        String reply = "Noted. I've removed this task:\n" +
                        t + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        System.out.println(reply);
    }


    public void printTasksOnSpecificDate(LocalDate date) {
        System.out.println("Here are the tasks on: " + date + "\n");
        int ListLength = taskList.size();
        for (int i = 0; i < ListLength; i++) {
            Task task = taskList.get(i);
            if (this.getTaskType(task).equals("Deadlines")) {
                Deadlines d = (Deadlines) task;
                if (d.getDate().equals(date)) {
                    System.out.println(d);
                }
            } else if (this.getTaskType(task).equals("Events")) {
                Events e = (Events) task;
                if (e.getDate().equals(date)) {
                    System.out.println(e);
                }
            }
        }
    }
    

    public String getTaskType(Task task) {
        if (task instanceof Deadlines) {
            return "Deadlines";
        } else if (task instanceof Events) {
            return "Events";
        } else if (task instanceof  ToDos) {
            return "ToDos";
        }
        return "";
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:\n");
        int ListLength = taskList.size();
        for (int i = 0; i < ListLength; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }


}
