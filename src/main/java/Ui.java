import java.util.Scanner;

public class Ui {

    protected final String name;
    protected final Scanner sc;
    protected final TaskList taskList;
    protected static final String BORDER = "____________________________________________________________";

    public Ui(String name, TaskList taskList) {
        this.name = name;
        this.sc = new Scanner(System.in);
        this.taskList = taskList;
    }

    public void introduce() {
        String line1 = String.format("Hello! I'm %s", this.name);
        String line2 = "What can I do for you?";
        System.out.println(String.format("%s\n%s\n%s\n%s\n", BORDER, line1, line2, BORDER));
    }

    public String readInput() {
        return this.sc.nextLine();
    }

    public String prettifyOutput(String input) {
        return String.format("%s\n%s\n%s\n", BORDER, input, BORDER);
    }

    public void printOutput(String output) {
        System.out.println(prettifyOutput(output));
    }
}
