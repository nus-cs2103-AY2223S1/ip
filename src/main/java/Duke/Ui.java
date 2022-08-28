package Duke;

import java.util.List;

public class Ui {
    /**
     * Prints welcome message.
     */
    public void welcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints out if the file has already been created.
     * @param create Boolean whether the file is already created.
     */
    public void fileCreate(boolean create) {
        if (create) {
            System.out.println("new file created");
        } else {
            System.out.println("File already exists");
        }
    }

    /**
     * Prints out depending on whether we are marking or unmarking a task.
     * @param mark Boolean deciding whether to mark or unmark.
     */
    public void markTask(boolean mark) {
        if (mark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    /**
     * Prints out information about adding a task.
     * @param total Number of tasks in total.
     * @param task Task added.
     */
    public void addTask(int total, Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have "+ total + " tasks in the list.");
    }

    /**
     * Prints out information about removing a task.
     * @param total Number of tasks in total.
     * @param task Task removed.
     */
    public void removeTask(int total, Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have "+ total + " tasks in the list.");

    }

    /**
     * Prints out all the tasks.
     * @param oldTasks Tasks that were previously created.
     * @param newTasks Tasks that were just created.
     */
    public void printTasks(List<Task> oldTasks, List<Task> newTasks){
        System.out.println("Here are the tasks in your list");
        for(int i = 0; i < oldTasks.size(); i++) {
            System.out.println(i+1+"."+oldTasks.get(i).toString());
        }
        if (!newTasks.isEmpty()) {
            for (int i = 0; i < newTasks.size(); i++) {
                System.out.println(i + 1+ oldTasks.size() + "." + newTasks.get(i).toString());
            }
        }
    }

    /**
     * Prints out exception statement.
     * @throws DukeException If command is not recognised.
     */
    public void displayError() throws DukeException {
        throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints out goodbye message.
     */
    public void goodBye() {
        System.out.println("Thank you for using the Duke bot!");
        System.out.println("Hope to see you again soon!");
    }

    /**
     * Prints out all the tasks that matches a given filter.
     * @param matchlist List of strings that match the find.
     */
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
