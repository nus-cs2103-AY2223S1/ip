import java.util.Scanner;

public class UI {
    Scanner reader;

    public UI() {
        reader = new Scanner(System.in);
    }

    protected void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    protected String readCommand() {
        return reader.nextLine();
    }

    protected void showError(DukeException e) {
        System.out.println(e);
    }
}
