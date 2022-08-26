import java.io.PrintStream;

public class Ui {
    private final PrintStream OUTPUT = System.out;

    public void printDivider() {
        OUTPUT.println("------------------------------");
    }

    public void printWithDivider(String s) {
        this.printDivider();
        OUTPUT.printf("%s", s);
        this.printDivider();
    }

    public void println(String s) {
        OUTPUT.println(s);
    }

    public void showWelcome() {
        this.printDivider();
        OUTPUT.println("Hello! I'm Duke\\nWhat can I do for you?\"");
        this.printDivider();
    }
}
