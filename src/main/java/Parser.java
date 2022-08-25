import exceptions.EmptyNameException;
import exceptions.InvalidTaskIndexException;
import exceptions.NoTasksException;
import exceptions.UnknownCommandException;
import objects.Task;

import java.util.List;
import java.util.Scanner;

public class Parser {
    private static Ui ui = new Ui();
    private static TaskList taskList = new TaskList();
    private static Scanner input = new Scanner(System.in);

    public static Scanner getScanner() {
        return input;
    }

    public List<Task> getTasks() { return taskList.getTasks(); }

    public void parseCommand(List<Task> tasks) {
        while (true) {
            try {
                // Scan input from the user
                String inputLine = input.nextLine();
                String[] inputs = inputLine.split(" ");
                String command = inputs[0];

                if (inputLine.equals(Duke.Command.BYE.name().toLowerCase())) {
                    Ui.endSession(input);
                    return;
                } else if (inputLine.equals(Duke.Command.LIST.name().toLowerCase())) {
                    Ui.showTasks(tasks);
                } else if (command.equals(Duke.Command.MARK.name().toLowerCase())) {
                    // inputs[1] is the index number of the task to be marked
                    if (inputs.length < 2) {
                        throw new InvalidTaskIndexException();
                    }
                    ui.markTaskAsDone(Integer.parseInt(inputs[1]), tasks);
                } else if (command.equals(Duke.Command.UNMARK.name().toLowerCase())) {
                    // inputs[1] is the index number of the task to be unmarked
                    if (inputs.length < 2) {
                        throw new InvalidTaskIndexException();
                    }
                    ui.markTaskAsNotDone(Integer.parseInt(inputs[1]), tasks);
                } else if (command.equals(Duke.Command.TODO.name().toLowerCase())) {
                    taskList.addTodo(inputs);
                } else if (command.equals(Duke.Command.DEADLINE.name().toLowerCase())) {
                    taskList.addDeadline(inputs);
                } else if (command.equals(Duke.Command.EVENT.name().toLowerCase())) {
                    taskList.addEvent(inputs);
                } else if (command.equals(Duke.Command.DELETE.name().toLowerCase())) {
                    // inputs[1] is the index number of the task to be marked
                    if (inputs.length < 2) {
                        throw new InvalidTaskIndexException();
                    }
                    taskList.deleteTask(Integer.parseInt(inputs[1]));
                } else if (command.equals(Duke.Command.FIND.name().toLowerCase())) {
                    // inputs[1] is the keyword (do not accept keywords)
                    taskList.findTasks(inputs[1]);
                } else {
                    // when none of the commands match
                    throw new UnknownCommandException();
                }
            } catch (EmptyNameException | UnknownCommandException | NoTasksException | InvalidTaskIndexException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
