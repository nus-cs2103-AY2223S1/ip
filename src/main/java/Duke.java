import java.util.Scanner;

public class Duke {
    private final TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printMessage("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input;

        while(true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                duke.printMessage(duke.taskList.toString());
            } else {
                duke.addTask(input);
            }
        }
        duke.printMessage("Bye. Hope to see you again soon!");
    }

    public void addTask(String task) {
        this.taskList.addTask(task);
        printMessage("added: " + task);
    }

    public void printMessage(String message) {
        System.out.println(outlineMessage(message));
    }

    private String outlineMessage(String message) {
        String line = "____________________________________________________________";
        String res = line + "\n" + message + "\n" +line;
        return res.replaceAll("(?m)^", "\t");
    }
}
