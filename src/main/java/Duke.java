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
            case "todo": case "deadline": case "event":
                this.addTask(commandArg, command);
                break;
            default:
                printMessage("Sorry, I don't understand your command :(");
                break;
        }
    }

    private void viewList() {
        this.printMessage(this.taskList.toString());
    }

    private void addTask(String task, String type) {
        Task newTask = null;
        switch (type) {
            case "todo" :
                newTask = new ToDo(task);
                break;
            case "deadline":
                String[] deadlineDesc = task.split("/by", 2);
                newTask = new Deadline(deadlineDesc[0], deadlineDesc[1]);
                break;
            case "event":
                String[] eventDesc = task.split("/at", 2);
                newTask = new Event(eventDesc[0], eventDesc[1]);
                break;
        }

        if (newTask != null) {
            this.taskList.addTask(newTask);
            printMessage("Got it. I've added this task:\n\t" + newTask
                    + "\nNow you have " + this.taskList.totalTask() + " tasks in the list.");
        }

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
