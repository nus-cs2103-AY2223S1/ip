import java.util.Scanner;

public class Ui {
    private static final String CHATBOT = "Pluto";
    private Scanner sc;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String introduction = String.format("\tHello I am %s.\n\tWhat can I do for you?", CHATBOT);
        System.out.println(introduction);
    }

    public String readCommand() {
        return sc.nextLine().strip();
    }

    public void print(StringBuilder message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void showError(String emessage) {
        System.out.println(ANSI_RED + emessage + ANSI_RESET);
    }
}
