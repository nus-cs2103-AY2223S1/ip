import java.util.ArrayList;
import java.util.Scanner;

public class InputParser {
    private Scanner sc;
    private ArrayList<Task> tasks;

    InputParser(Scanner sc, ArrayList<Task> tasks) {
        this.sc = sc;
        this.tasks = tasks;
    }

    public void parseInputs() {
        String input;
        while (true) {
            input = this.sc.nextLine();
            Command command;
            if (input.equals("")) {
                command = new Command(CommandType.EMPTY, tasks, input);
            } else if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                command = new Command(CommandType.LIST, tasks, input);
            } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                command = new Command(CommandType.TODO, tasks, input);
            } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                command = new Command(CommandType.DEADLINE, tasks, input);
            }  else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                command = new Command(CommandType.EVENT, tasks, input);
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                command = new Command(CommandType.MARK, tasks, input);
            } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                command = new Command(CommandType.UNMARK, tasks, input);
            } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                command = new Command(CommandType.DELETE, tasks, input);
            } else {
                command = new Command(CommandType.INVALID, tasks, input);
            }
            try {
                command.run();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
