package duke.ui;

import java.util.Scanner;

import duke.data.TaskList;

public class Ui {

    private Scanner sc;


    // Constructor
    public Ui() {
        sc = new Scanner(System.in);
    }


    public String readCommand() {
        return sc.nextLine();
    }


    public void stopReadingUserInput() {
        sc.close();
    }
    
    
    public void listTasks(TaskList tasks) {
        String result = "Here are the tasks in your list:\n";
        
        for (int i = 0; i < tasks.getSize(); i++) {
            String line = String.format("%d. %s\n", i + 1, tasks.getTask(i));
            result = result.concat(line);
        }
        
        System.out.println(result);
    }

    
    // Print a message given by the caller
    public void printMessage(String message) {
        System.out.println(message);
    }


    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }


    public void printInvalidCommandMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }


    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
