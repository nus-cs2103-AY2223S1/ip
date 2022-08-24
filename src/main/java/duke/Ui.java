package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    public void line() {
        System.out.println("________________________________________");
    }

    public String parseCommand() {
        return this.scanner.nextLine();
    }
}
