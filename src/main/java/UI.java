import java.util.Scanner;

public class UI {

    private Scanner sc;

    private static String initGreeting = "Hello! I'm Duke\nWhat can I do for you?";

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public String greet() {
        return initGreeting;
    }

    public String list() {
        return "list";
    }

    public String blah() {
        return "blah";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String getInput() {
        return sc.nextLine();
    }
}
