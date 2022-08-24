package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    final static String LOGO =
            "   __ __    ____       ___               __\n" +
                    "  / // /__ / / /__    / _ \\___ ____  ___/ /__ _\n" +
                    " / _  / -_) / / _ \\  / ___/ _ `/ _ \\/ _  / _ `/\n" +
                    "/_//_/\\__/_/_/\\___/ /_/   \\_,_/_//_/\\_,_/\\_,_/\n";
    public Ui() {
        this.scanner = new Scanner(System.in);

    }

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("How can I help you today? :)");
    }

    public String nextCommand() {
        return this.scanner.nextLine();
    }

    public void displayList(TaskList tasks) {
        System.out.println(tasks.displayList());
    }

    public void displayMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showBye() {
        this.scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("There was an issue opening the file!");
    }

}
