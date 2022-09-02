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

    public String markTask2(boolean mark) {
        if (mark) {
            return "Nice! I've marked this task as done:\n";
        } else {
            return "OK, I've marked this task as not done yet:\n";
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

    public String addTask2(int total, Task task) {
        String stringReturned = "Got it. I've added this task: \n" + task.toString() + "\n" +
                "Now you have "+ total + " tasks in the list.\n";
        return stringReturned;
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

    public String removeTask2(int total, Task task) {
        String stringReturned = "Noted. I've removed this task:\n";
        stringReturned = stringReturned + task.toString() + "\n";
        stringReturned = stringReturned + "Now you have "+ total + " tasks in the list.\n";
        return stringReturned;
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

    public String printTasks2(List<Task> oldTasks, List<Task> newTasks){
        String stringReturned = "Here are the tasks in your list\n";
        for(int i = 0; i < oldTasks.size(); i++) {
            String oldTask = i+1+"."+oldTasks.get(i).toString() + "\n";
            stringReturned = stringReturned + oldTask;
        }
        if (!newTasks.isEmpty()) {
            for (int i = 0; i < newTasks.size(); i++) {
                String newTask = i + 1+ oldTasks.size() + "." + newTasks.get(i).toString()+ "\n";
                stringReturned = stringReturned + newTask;
            }
        }
        return stringReturned;
    }


    /**
     * Prints out exception statement.
     * @throws DukeException If command is not recognised.
     */
    public void displayError() throws DukeException {
        throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String displayError2() throws DukeException {
        throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        //return ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints out goodbye message.
     */
    public void goodBye() {
        System.out.println("Thank you for using the Duke bot!");
        System.out.println("Hope to see you again soon!");
    }

    /**
     * Prints out goodbye message.
     */
    public String goodBye2() {
        return "Thank you for using the Duke bot!\n"+ "Hope to see you again soon!\n";
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

    public String printMatches2(List<String> matchlist) {
        String stringReturned= "";
        if (matchlist.isEmpty()) {
            stringReturned = "Sorry! We are unable to find any matching tasks in your list!";
        } else{
            stringReturned = "Here are the matching tasks in your list:\n";
            for(int i = 0;i < matchlist.size(); i++) {
                String matches = i+1 + "." + matchlist.get(i) + "\n";
                stringReturned = stringReturned + matches;
            }
        }
        return stringReturned;
    }




}
