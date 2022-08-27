import java.util.Scanner;

public class Ui {
    protected Scanner in;
    protected String nextCommand;

    public Ui() {
        this.in = new Scanner(System.in);
        this.nextCommand = in.nextLine();
    }

    public Ui(Scanner in) {
        this.in = in;
        this.nextCommand = in.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return this.nextCommand;
    }

    public void changeCommand() {
        this.nextCommand = this.in.nextLine();
    }

    public void nextCommand() {
        this.in.nextLine();
    }

    public void showLoadingError() throws DukeException {
        try {
            throw new DukeException("The file is empty!");
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLine() {
        System.out.println("---------------------------------");
    }

}
