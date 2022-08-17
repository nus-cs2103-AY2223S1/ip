import java.util.*;
class SkeletonDuke {
    private List<Task> list;
    private static int noOfTasks;

    SkeletonDuke() {
        this.list = new ArrayList<Task>();
        this.noOfTasks = 0;
    }

    void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void add(String s) {
        String[] strarr = s.split(" ");
        String typeOfTask = strarr[0];
        String description = s;
        String date = s;
        Task newTask = new Task(s);
        if(typeOfTask.equals("todo")) {
            description = this.processDescription(strarr);
            newTask = new ToDo(description);
        } else if(typeOfTask.equals("deadline")) {
            description = this.processDescription(strarr);
            newTask = new Deadline(description);
        } else if(typeOfTask.equals("event")) {
            description = this.processDescription(strarr);
            newTask = new Event(description);
        }
        list.add(newTask);
        this.noOfTasks++;
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + this.noOfTasks + " tasks in the list.");

    }

    void getList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i < list.size() + 1; i++) {
            String currentTask = list.get(i - 1).toString();
            System.out.println(i + ". "+ currentTask);
        }
    }

    void mark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToBeModify.toString());
    }

    void unmark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.unmarked();
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(taskToBeModify.toString());
    }

    String processDescription(String[] strarr) {
        String description = strarr[1];
        for(int i = 2; i < strarr.length; i++) {
            description = description + " " + strarr[i];
        }
        return description;
    }


}