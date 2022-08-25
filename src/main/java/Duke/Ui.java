package Duke;

import java.util.List;

public class Ui {
    public void welcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void fileCreate(boolean create) {
        if (create) {
            System.out.println("new file created");
        } else {
            System.out.println("File already exists");
        }
    }

    public void markTask(boolean mark) {
        if (mark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    public void addTask(int total, Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have "+ total + " tasks in the list.");
    }

    public void removeTask(int total, String task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have "+ total + " tasks in the list.");

    }

    public void printTasks(List<String> oldTasks, List<String> newTasks){
        System.out.println("Here are the tasks in your list");
        for(int i = 0; i < oldTasks.size(); i++) {
            System.out.println(i+1+"."+oldTasks.get(i));
        }
        if (!newTasks.isEmpty()) {
            for (int i = 0; i < newTasks.size(); i++) {
                System.out.println(i + 1+ oldTasks.size() + "." + newTasks.get(i));
            }
        }
    }
    public void displayError() throws DukeException {
        throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void goodBye() {
        System.out.println("Thank you for using the Duke bot!");
        System.out.println("Hope to see you again soon!");
    }

    public void printMatches(List<String> matchlist) {
        if (matchlist.isEmpty()) {
            System.out.println("Sorry! We are unable to find any matching tasks in your list!");
        } else{
            System.out.println("Here are the matching tasks in your list:");
            for(int i = 0;i < matchlist.size(); i++) {
                System.out.println(i+1 + "." + matchlist.get(i));
            }
        }



    }




}
