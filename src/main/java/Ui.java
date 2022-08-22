import java.util.Scanner;

/**
 * The Ui class deals with interaction with the user.
 */
public class Ui {
    public void greetUser() {
        System.out.println("  ____________________________________________________________");
        System.out.println("  Hello from Sky!\n  Your heavenly chatbot to help you track your things.");
        System.out.println("  ____________________________________________________________");
    }

    public void showLine() {
        System.out.println("  ____________________________________________________________");
    }

    public String readCommand(Scanner scanner) {
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }

    public void endConvo() {
        System.out.println("  Bye. May all your endeavours fly high!");
    }
}
