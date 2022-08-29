import java.util.Scanner;

public class UI {
    Scanner scanner;
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void welcomeUser() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public String readInput() {
        return scanner.nextLine();
    }
}
