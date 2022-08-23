package roofus;

import roofus.task.Task;

import java.util.Scanner;

public class Ui {
    static String LINESEP = "****************************************";
    private Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);    
    }
    
    public void greet() {
        System.out.println(LINESEP);
        System.out.println("Hello I'm Roofus\n" + "What can I do for you?");
        System.out.println(LINESEP);
    }

    public void signOff() {
        System.out.println(String.format("%s\nBye. Hope to see you again soon!\n%s",
                LINESEP, LINESEP));
    }

    public void errMessage(String message) {
        if (message.isEmpty()) {
            System.out.println("!!!!!");
        }
        System.out.println(LINESEP);
        System.out.println(message.toUpperCase());
        System.out.println(LINESEP);
    }
    
    public void delete(String task, int taskLength) {
        System.out.println(LINESEP);
        System.out.println(String.format("Noted. I've removed this task:\n%s\n" +
                        "Now you have %d tasks in the list.", task.toString(), taskLength));
        System.out.println(LINESEP);
    }
    
    public void list(TaskList taskList) {
        System.out.println(LINESEP);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length(); i++) {
            int index = i + 1;
            System.out.println(index + "." + taskList.getTask(i).toString());
        }
        System.out.println(LINESEP);
    }
    
    public void filterList(TaskList taskList, String key) {
        System.out.println(LINESEP);
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(key)) {
                System.out.println(count + "." + taskString);
                count++;
            }
        }
        System.out.println(LINESEP);
    }
    
    public void mark(Task task) {
        System.out.println(LINESEP);
        System.out.println("Nice! I've marked this task as done:\n" +
                task.toString());
        System.out.println(LINESEP);
    }
    
    public void unmark(Task task) {
        System.out.println(LINESEP);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                task.toString());
        System.out.println(LINESEP);
    }
    
    public void addTask(Task task, int taskLength) {
        String reply = String.format("%s\nGot it. I've added this task:\n%s\n" +
                        "Now you have %d tasks in the list.\n%s", LINESEP, task.toString(),
                taskLength, LINESEP);
        System.out.println(reply);
    }
    
    public void clearStorage() {
        System.out.println("Storage has been cleared :)");
    }
    
    public String readCommand() {
        return sc.nextLine();
    }
}
