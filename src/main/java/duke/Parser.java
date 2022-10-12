package duke;

import duke.commands.*;

/**
 * The Parser class parses any command inputted by the user
 */
public class Parser {

    /**
     * A constructor for the Parser class
     *
     * @param input The command inputted by the user
     * @param tl The <code>TaskList</code> given to manage
     * @return A <code>Command</code> to be executed
     * @throws DukeException If there is a Duke-specific error encountered
     */
    public static Command parse(String input, TaskList tl) throws DukeException {
        String[] inputs = input.split(" ");

        switch (inputs[0].toUpperCase()) {
        case "LIST":
            return new ListCommand();
        case "MARK":
            /*if (inputs.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            int taskNum = Integer.parseInt(inputs[1]) - 1;
            assert taskNum > -1 : "The task number should be a valid number (starting from 1)";
            response = tl.markTask(taskNum);
            break;*/
            return new MarkCommand(inputs);
        case "UNMARK":
            /*if (inputs.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            taskNum = Integer.parseInt(inputs[1]) - 1;
            assert taskNum > -1 : "The task number should be a valid number (starting from 1)";
            response = tl.unmarkTask(taskNum);
            break;*/
            return new UnmarkCommand(inputs);
        case "TODO":
            /*if (inputs.length < 2) {
                throw new DukeException("The description of a Todo task cannot be empty");
            }
            String[] taskDesc = Arrays.copyOfRange(inputs, 1, inputs.length);
            response = tl.createToDo(taskDesc);
            break;*/
            return new AddTodoCommand(inputs);
        case "DEADLINE":
            /*taskDesc = Arrays.copyOfRange(inputs, 1, inputs.length);
            taskDesc = String.join(" ", taskDesc).split("/by ");
            if (taskDesc.length < 2) {
                throw new DukeException("You're missing some details!\nRemember to include the description "
                        + "followed by '/by [INSERT DATETIME]'");
            }
            response = tl.createDeadline(taskDesc);
            break;*/
            return new AddDeadlineCommand(inputs);
        case "EVENT":
            /*taskDesc = Arrays.copyOfRange(inputs, 1, inputs.length);
            taskDesc = String.join(" ", taskDesc).split("/at ");
            if (taskDesc.length < 2) {
                throw new DukeException("You're missing some details!\nRemember to include the description "
                        + "followed by '/at [INSERT DATETIME]'");
            }
            response = tl.createEvent(taskDesc);
            break;*/
            return new AddEventCommand(inputs);
        case "DELETE":
            /*if (inputs.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            taskNum = Integer.parseInt(inputs[1]) - 1;
            assert taskNum > -1 : "The task number should be a valid number (starting from 1)";
            response = tl.deleteTask(taskNum);
            break;*/
            return new DeleteCommand(inputs);
        case "BYE":
            return new ByeCommand();
        case "FIND":
            /*if (inputs.length < 2) {
                throw new DukeException("Please enter a keyword to search!");
            }
            String keyword = inputs[1];
            response = tl.findTask(keyword);
            break;*/
            return new FindCommand(inputs);
        case "SNOOZE":
            /*if (inputs.length < 5) {
                throw new DukeException("You're missing some details!\n" +
                        "The command format should be: snooze TASK_NUMBER /by DATE TIME");
            }
            taskDesc = Arrays.copyOfRange(inputs, 1, inputs.length);
            taskDesc = String.join(" ", taskDesc).split("/by ");
            response = tl.snoozeTask(taskDesc);
            break;*/
            return new SnoozeCommand(inputs);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means");
        }
    }
}
