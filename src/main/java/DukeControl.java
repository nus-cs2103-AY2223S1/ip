import java.util.ArrayList;
import java.util.Arrays;

public class DukeControl {
    public ArrayList<Task> arrayList;

    public DukeControl() {
        this.arrayList = new ArrayList<>();
    }

    public void evaluate(String input) throws DukeException {
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
            throw new InvalidCommandException();
        }
    }

    public void parseList(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length != 0) {
            throw new InvalidArgumentException();
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, arrayList.get(i).print()));
            }
        }
    }

    public void parseMark(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
            throw new InvalidArgumentException();
        } else {
            arrayList.get(Integer.parseInt(commandArgs[0]) - 1).mark();
        }
    }

    public void parseUnmark(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
            throw new InvalidArgumentException();
        } else {
            arrayList.get(Integer.parseInt(commandArgs[0]) - 1).unmark();
        }
    }

    public void parseTodo(String[] commandArgs) throws EmptyTitleException {
        String title = String.join(" ", commandArgs);

        if (title == "") {
            throw new EmptyTitleException();
        } else {
            this.addTask(new ToDo(title));
        }
    }

    public void parseDeadline(String[] commandArgs) throws DukeException {
        if (!Arrays.asList(commandArgs).contains("/by")) {
            throw new InvalidArgumentException();
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/by");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String deadline = String.join(" ", Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));

            if (title == "") {
                throw new EmptyTitleException();
            } else if (deadline == "") {
                throw new InvalidArgumentException();
            } else {
                this.addTask(new Deadline(title, deadline));
            }
        }
    }

    public void parseEvent(String[] commandArgs) throws DukeException {
        if (!Arrays.asList(commandArgs).contains("/at")) {
            throw new InvalidArgumentException();
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/at");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String time = String.join(" ", Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));

            if (title == "") {
                throw new EmptyTitleException();
            } else if (time == "") {
                throw new InvalidArgumentException();
            } else {
                this.addTask(new Event(title, time));
            }
        }
    }

    public void addTask(Task newTask) {
        arrayList.add(newTask);
        System.out.println(String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                newTask.print(), this.arrayList.size()));
    }
}
