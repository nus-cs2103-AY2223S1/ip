import java.util.Scanner;

public class Duke {
    private final TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }
    public static void main(String[] args) {
        final Duke duke = new Duke();
        duke.printMessage("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            duke.receiveCommand(input);
        }
        duke.printMessage("Bye. Hope to see you again soon!");
    }

    public void receiveCommand(String input) {
        String[] arg = input.split(" ", 2);
        String command = arg[0];
        String commandArg = arg.length > 1 ? arg[1] : "";

        switch (command) {
            case "list":
                this.viewList();
                break;
            case "mark":
                this.markTask(commandArg);
                break;
            case "unmark":
                this.unmarkTask(commandArg);
                break;
            default:
                addTask(input);
                break;
        }
    }

    private void viewList() {
        this.printMessage(this.taskList.toString());
    }

    private void addTask(String task) {
        this.taskList.addTask(task);
        printMessage("added: " + task);
    }

    private void markTask(String n) {
        // handle cases when n is invalid
        int index = Integer.parseInt(n);
        this.taskList.markTaskN(index, true);
        printMessage("Nice! I've marked this task as done: \n\t" + this.taskList.getTaskN(index));
    }

    private void unmarkTask(String n) {
        // handle cases when n is invalid
        int index = Integer.parseInt(n);
        this.taskList.markTaskN(index, false);
        printMessage("OK, I've marked this task as not done yet: \n\t" + this.taskList.getTaskN(index));
    }

    public void printMessage(String message) {
        String line = "____________________________________________________________";
        String res = line + "\n" + message + "\n" +line;
        res = res.replaceAll("(?m)^", "\t");
        System.out.println(res);
    }
}
