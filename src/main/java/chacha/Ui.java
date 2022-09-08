package chacha;

import java.util.Scanner;
import chacha.tasks.Task;

/**
 * Handles all user interaction.
 */
public class Ui {
  
    /** 
     * Takes in user typed input as a string.
     * 
     * @return User input as string.
     */
    public String readInput() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        if (s == "exit") {
            input.close();
        }
        return s;
    }

    /**
     * Prints Chacha welcome message.
     *  
     * @return Welcome message as a string.
     */
    public String printWelcome() {
        return "Welcome! I'm Chacha.\n" + "How may I assist you?";
    }

    /**
     * Prints each task in given task list.
     *  
     * @param taskList Task list to print tasks from.
     */
    public void printList(TaskList taskList) {
        for (int i = 0; i < taskList.getSize();i++) {
            Task t = taskList.get(i);	      
            System.out.println(i + 1 + 
                "." + 
                t.toString()); 		
        }   
    }

    /** 
     * Prints tasks that has been added to task list.
     * 
     * @param task Task to add into given task list.
     * @param taskList Task list to add given task to.
     */
    public void printAdd(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
            System.out.println(task.toString()); 
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    /** 
     * Prints tasks that has been deleted from task list.
     * 
     * @param task Task to be deleted from task list.
     * @param size Number of remaining tasks in task list.
     */
    public void printDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    
    /** 
     * Prints tasks that has been marked as done.
     * 
     * @param task Task to be marked.
     */
    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    
    /** 
     * Prints task that has been unmarked as done.
     * 
     * @param task Task to be unmarked.
     */
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

    /**
     * Prints custom error message.
     * 
     * @param message Custom error message to be printed.
     */
    public void printError(String message) {
            System.out.println("Chacha error: " + message);
    }
    
}
