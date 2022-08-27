package duke;

import java.util.Scanner;

/**
 * Ui deals with the output that the user can see.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class Ui {

    private Scanner scanner;
    final static String LOGO =
            "   __ __    ____       ___               __\n" +
                    "  / // /__ / / /__    / _ \\___ ____  ___/ /__ _\n" +
                    " / _  / -_) / / _ \\  / ___/ _ `/ _ \\/ _  / _ `/\n" +
                    "/_//_/\\__/_/_/\\___/ /_/   \\_,_/_//_/\\_,_/\\_,_/\n";

    /**
     * Ui constructor that creates an instance of the Ui Object
     */
    public Ui() {
        this.scanner = new Scanner(System.in);

    }

    /**
     * A method that displays a welcome screen to the user.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("How can I help you today? :)");
    }

    /**
     * A method that gets the next line of the user input from
     * the Scanner Object.
     *
     * @return String the next line of the user input obtained.
     */
    public String nextCommand() {
        return this.scanner.nextLine();
    }

    /**
     * A method to display the tasks within a given TaskList.
     *
     * @param tasks The TaskList that needs to be displayed.
     */
    public void displayList(TaskList tasks) {
        System.out.println(tasks.displayList());
    }

    /**
     * A method to display a bye sequence to the user.
     */
    public void showBye() {
        this.scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

}
