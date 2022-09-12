package duke;
import duke.commands.*;
import duke.ui.Ui;

public class Parser {

    public static Command parseInput(String input, Ui ui, Storage storage, TaskList taskList) {
        String[] breakitdown = input.split(" ");
        String command = breakitdown[0];
            if (command.equals("bye")) {
                //return new ByeCommand(ui, storage, taskList);
                storage.write(taskList);
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                return null;
            } else if (command.equals("list")) {
                return new ListCommand(ui, taskList);
            } else if (command.equals("unmark")) {
                int idx = Integer.parseInt(breakitdown[1]);
                return new UnmarkCommand(idx, taskList, ui);
            } else if (command.equals("mark")) {
                int idx = Integer.parseInt(breakitdown[1]);
                return new MarkCommand(idx, taskList, ui);
            } else if (command.equals("todo")) {
                return new ToDoCommand(input, ui, taskList);
            } else if (command.equals("deadline")) {
                return new DeadlineCommand(input, ui, taskList);
            } else if (command.equals("event")) {
                return new EventCommand(input, ui, taskList);
            } else if (command.equals("delete")) {
                int idx = Integer.parseInt(breakitdown[1]);
                return new DeleteCommand(idx, ui, taskList);
            } else if (command.equals("find")) {
                String toFind = breakitdown[1];
                return new FindCommand(toFind, taskList, ui);
            } else {
                return new UnknownCommand();
            }
    }
}
