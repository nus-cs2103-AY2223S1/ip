import java.util.ArrayList;
import java.util.Arrays;

public class DukeControl {
    public ArrayList<Task> arrayList;

    public DukeControl() {
        this.arrayList = new ArrayList<>();
    }

    public void evaluate(String input) {
        String[] command = input.split(" ");
        String mainCommand = command[0];
        String[] commandArgs = Arrays.copyOfRange(command, 1, command.length);

        if (mainCommand.equals("list")) {
            this.parseList(commandArgs);
        } else if (mainCommand.equals("mark")) {
            this.parseMark(commandArgs);
        } else if (mainCommand.equals("unmark")) {
            this.parseUnmark(commandArgs);
        } else if (mainCommand.equals("todo")) {
            this.parseTodo(commandArgs);
        } else if (mainCommand.equals("deadline")) {
            this.parseDeadline(commandArgs);
        } else if (mainCommand.equals("event")) {
            this.parseEvent(commandArgs);
        } else {
            System.out.println("Invalid command: " + mainCommand);
        }
    }

    public void parseList(String[] commandArgs) {
        if (commandArgs.length != 0) {
            System.out.println("command LIST: Did you mean list?");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, arrayList.get(i).print()));
            }
        }
    }

    public void parseMark(String[] commandArgs) {
        if (commandArgs.length != 1) {
            System.out.println("command MARK: Invalid argument number");
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
            System.out.println("command MARK: Invalid task index");
        } else {
            arrayList.get(Integer.parseInt(commandArgs[0]) - 1).mark();
        }
    }

    public void parseUnmark(String[] commandArgs) {
        if (commandArgs.length != 1) {
            System.out.println("command UNMARK: Invalid argument number");
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
            System.out.println("command UNMARK: Invalid task index");
        } else {
            arrayList.get(Integer.parseInt(commandArgs[0]) - 1).unmark();
        }
    }

    public void parseTodo(String[] commandArgs) {
        String title = String.join(" ", commandArgs);
        this.addTask(new ToDo(title));
    }

    public void parseDeadline(String[] commandArgs) {
        if (!Arrays.asList(commandArgs).contains("/by")) {
            System.out.println("command DEADLINE: Invalid arguments /by not found");
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/by");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String deadline = String.join(" ", Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));
            this.addTask(new Deadline(title, deadline));
        }
    }

    public void parseEvent(String[] commandArgs) {
        if (!Arrays.asList(commandArgs).contains("/at")) {
            System.out.println("command EVENT: Invalid arguments /at not found");
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/at");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String time = String.join(" ", Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));
            this.addTask(new Event(title, time));
        }
    }

    public void addTask(Task newTask) {
        arrayList.add(newTask);
        System.out.println(String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                newTask.print(), this.arrayList.size()));
    }
}
