package roofus;

import roofus.task.Task;

import java.util.Scanner;

public class Ui {
    private static String LINE_SEP = "****************************************";
    private Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);    
    }
    
    public void greet() {
        System.out.println(LINE_SEP);
        System.out.println("Hello I'm Roofus\n" 
                + "What can I do for you?");
        System.out.println(LINE_SEP);
    }

    public void signOff() {
        System.out.println(String.format("%s\nBye. Hope to see you again soon!\n%s",
                LINE_SEP, LINE_SEP));
    }

    public void printErrMessage(String message) {
        if (message.isEmpty()) {
            System.out.println("!!!!!");
        }
        System.out.println(LINE_SEP);
        System.out.println(message.toUpperCase());
        System.out.println(LINE_SEP);
    }
    
    public void delete(String task, int taskLength) {
        System.out.println(LINE_SEP);
        System.out.println(String.format("Noted. I've removed this task:\n%s\n" 
                + "Now you have %d tasks in the list.", task.toString(), taskLength));
        System.out.println(LINE_SEP);
    }
    
    public void list(TaskList taskList) {
        System.out.println(LINE_SEP);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length(); i++) {
            int index = i + 1;
            System.out.println(index + "." + taskList.getTask(i).toString());
        }
        System.out.println(LINE_SEP);
    }
    
    public void mark(Task task) {
        System.out.println(LINE_SEP);
        System.out.println("Nice! I've marked this task as done:\n" 
                + task.toString());
        System.out.println(LINE_SEP);
    }
    
    public void unmark(Task task) {
        System.out.println(LINE_SEP);
        System.out.println("OK, I've marked this task as not done yet:\n" 
                + task.toString());
        System.out.println(LINE_SEP);
    }
    
    public void addTask(Task task, int taskLength) {
        String reply = String.format("%s\nGot it. I've added this task:\n%s\n" 
                        + "Now you have %d tasks in the list.\n%s", 
                            LINE_SEP, task.toString(), taskLength, LINE_SEP);
        System.out.println(reply);
    }
    
    public void clearStorage() {
        System.out.println("Storage has been cleared :)");
    }
    
    public String readCommand() {
        return sc.nextLine();
    }
}
