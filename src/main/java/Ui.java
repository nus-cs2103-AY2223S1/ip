import java.util.Scanner;

public class Ui {

    private final Scanner sc;



    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\n What can I do for you?");
        System.out.println("____________________________________");
    }

    public String readInput() {
        return sc.nextLine().trim();
    }

    public void printLine(Object line) {
        System.out.println(line);
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printDash() {
        System.out.println("___________________________________");
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}


