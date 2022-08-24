package duke;

import java.util.Scanner;

/**
 * Represents an interface that interacts with the user by printing the necessary messages
 */
public class Ui {

    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
    }

    void printLine() {
        System.out.println("-------------------------------------------------");
    }


    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    void showError(Exception e){
        System.out.println(e.getMessage());
    }

}
