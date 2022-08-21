import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }

    public void showJeanError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showGeneralError(String errorMessage) {
        System.out.println("Something went wrong: " + errorMessage
                           + "\nQuelque chose a mal tourné: " + errorMessage);
    }

    public void greet() {
        System.out.println("Hello! I'm Jean"
                           + "\nHow can I help you?"
                           + "\nBonjour! Je m'appelle Jean"
                           + "\nVous désirez?");
    }

    public void end() {
        System.out.println("\tGoodbye! See you soon!"
                           + "\n\tAu revoir! À tout à l'heure!");
    }
}
