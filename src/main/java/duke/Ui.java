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
     *
     * @param create Boolean whether the file is already created.
     */
    public void fileCreate(boolean create) {
        if (create) {
            System.out.println("new file created");
        } else {
            System.out.println("File already exists");
        }
    }


    public String markTask2(boolean mark) {
        if (mark) {
            return "Nice! I've marked this task as done:\n";
        } else {
            return "OK, I've marked this task as not done yet:\n";
        }
    }

    public String addTask2(int total, Task task) {
        String stringReturned = "Got it. I've added this task: \n" + task.toString() + "\n" +
                "Now you have "+ total + " tasks in the list.\n";
        return stringReturned;
    }


    public String removeTask2(int total, Task task) {
        String stringReturned = "Noted. I've removed this task:\n";
        stringReturned = stringReturned + task.toString() + "\n";
        stringReturned = stringReturned + "Now you have "+ total + " tasks in the list.\n";
        return stringReturned;
    }

    public String printTasks2(List<Task> oldTasks){
        String stringReturned = "Here are the tasks in your list\n";
        if(oldTasks.isEmpty()) {
            stringReturned += "your tasklist is empty\n";
        } else {
            for(int i = 0; i < oldTasks.size(); i++) {
                String oldTask = i+1+"."+oldTasks.get(i).toString() + "\n";
                stringReturned = stringReturned + oldTask;
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
