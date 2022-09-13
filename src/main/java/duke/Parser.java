package duke;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.MarkCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnknownCommand;
import duke.commands.UnmarkCommand;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Parser {

    public static Command parseInput(String input, Ui ui, Storage storage, TaskList taskList) {
        assert input != null;
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
            if (command.equals("bye")) {
                storage.write(taskList);
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                return null;
            } else if (command.equals("list")) {
                return new ListCommand(ui, taskList);
            } else if (command.equals("unmark")) {
                int idx = Integer.parseInt(splitInput[1]);
                return new UnmarkCommand(idx, taskList, ui);
            } else if (command.equals("mark")) {
                int idx = Integer.parseInt(splitInput[1]);
                return new MarkCommand(idx, taskList, ui);
            } else if (command.equals("todo")) {
                return new ToDoCommand(input, ui, taskList);
            } else if (command.equals("deadline")) {
                return new DeadlineCommand(input, ui, taskList);
            } else if (command.equals("event")) {
                return new EventCommand(input, ui, taskList);
            } else if (command.equals("delete")) {
                int idx = Integer.parseInt(splitInput[1]);
                return new DeleteCommand(idx, ui, taskList);
            } else if (command.equals("find")) {
                String toFind = splitInput[1];
                return new FindCommand(toFind, taskList, ui);
            } else {
                return new UnknownCommand();
            }
    }
}
