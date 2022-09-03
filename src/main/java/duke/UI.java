package duke;

import java.util.Scanner;

/**
 * Represents user interactions like getting user inputs and printing to the user's screen.
 */
public class UI {
    private Scanner sc = new Scanner(System.in);
    
    public String readCommand() {
        return sc.nextLine();
    }
    
    public void showLine() {
        System.out.println("_______\n");
    }
    
    public void showError(Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }
    
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public void print(String string) {
        System.out.print(string);
    }
}
