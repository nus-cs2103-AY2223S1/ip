import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showText(String text) {
        System.out.println(text);
    }

    public void showInstructions() {

        System.out.println("==========================");
        System.out.println("Hey, I am Maria. I can manage your tasks for you"); // Welcome text
        System.out.println("Type 'todo' to create a todo.");
        System.out.println("Type 'deadline' to create a task with deadline.");
        System.out.println("Type 'event' to create an event with a start and end time.");
        System.out.println("Type 'list' to list out all your tasks.");
        System.out.println("Type 'mark' to complete a task.");
        System.out.println("Type 'unmark' to un-complete a task.");
        System.out.println("Type 'delete' to remove a task.");
        System.out.println("Type 'bye' to finish the conversation.");
        System.out.println("==========================");

    }

    public void showNewLine() {
        System.out.println();
    }

    public void showDivider() {
        System.out.println("=========================");
    }

    public String readCommand() {
        System.out.print("Type in your command for Maria: ");
        return this.scanner.nextLine();
    }

}
