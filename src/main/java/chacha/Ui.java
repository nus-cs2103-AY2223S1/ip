package chacha;
import java.util.Scanner;

import chacha.tasks.Task;

public class Ui {

    public String readInput() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        if (s == "exit") {
            input.close();
        }
        return s;
    }

    public String printWelcome() {
        return "Welcome! I'm Chacha.\n" + "How may I assist you?";
    }

    public void printList(TaskList taskList) {
        for (int i = 0; i < taskList.getSize();i++) {
            Task t = taskList.get(i);	      
            System.out.println(i + 1 + 
                "." + 
                t.toString()); 		
        }   
    }

    public void printAdd(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
            System.out.println(task.toString()); 
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public void printDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void printUnmark(Task task) {
        System.out.println("Nice! I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Prints tasks from task list containing certain keyword.
     * 
     * @param taskList TaskList with tasks containing certain keyword.
     */
    public void printFind(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("There are no matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.getSize();i++) {
                Task t = taskList.get(i);	      
                System.out.println(i + 1 + 
                    "." + 
                    t.toString()); 		
            } 
        }
    }

    public void printError(String message) {
        System.out.println("Chacha error: " + message);
    }
    
}
