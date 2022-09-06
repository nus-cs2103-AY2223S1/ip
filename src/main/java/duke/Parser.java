package duke;

/**
 * The class that deals with user commands.
 */
public class Parser {
    private final Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Starts the parser with the input.
     *
     * @param input The input that is being placed into the parser.
     * @return The appropriate response to the input.
     */
    public String parse(String input) {
        if (input.equals("list")) {
            return duke.ui.printTaskList(duke.tasks);
        } else if (input.startsWith("mark ")) {
            try {
                int number = Integer.parseInt(input.substring(5));
                duke.tasks.mark(number);
                return duke.ui.printMarkTaskMessage("mark", duke.tasks.getTask(number - 1));
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            } catch (StringIndexOutOfBoundsException e) {
                return duke.ui.printMissingNumberAfterCommandError("mark");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int number = Integer.parseInt(input.substring(7));
                duke.tasks.unMark(number);
                return duke.ui.printMarkTaskMessage("unmark", duke.tasks.getTask(number - 1));
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            } catch (StringIndexOutOfBoundsException e) {
                return duke.ui.printMissingNumberAfterCommandError("unmark");
            }
        } else if (input.startsWith("todo ")) {
            try {
                input = input.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                return duke.ui.printSomethingMissingError("todo");
            }
            ToDo toDo = new ToDo(input);
            duke.tasks.addTask(toDo);
            return duke.ui.printAddTaskMessage(toDo) + duke.ui.printNumberOfTasksMessage(duke.tasks);
        } else if (input.startsWith("deadline ")) {
            int end = input.indexOf("/by ");
            String textInput;
            String byInput;
            try {
                textInput = input.substring(9, end);
                byInput = input.substring(end + 4);
            } catch (StringIndexOutOfBoundsException e) {
                return duke.ui.printSomethingMissingError("deadline");
            }
            Deadline deadline = new Deadline(textInput, byInput);
            duke.tasks.addTask(deadline);
            return duke.ui.printAddTaskMessage(deadline) + duke.ui.printNumberOfTasksMessage(duke.tasks);
        } else if (input.startsWith("event ")) {
            int end = input.indexOf("/at ");
            String textInput;
            String atInput;
            try {
                textInput = input.substring(6, end);
                atInput = input.substring(end + 4);
            } catch (StringIndexOutOfBoundsException e) {
                return duke.ui.printSomethingMissingError("event");
            }
            Event event = new Event(textInput, atInput);
            duke.tasks.addTask(event);
            return duke.ui.printAddTaskMessage(event) + duke.ui.printNumberOfTasksMessage(duke.tasks);
        } else if (input.startsWith("delete ")) {
            try {
                int number = Integer.parseInt(input.substring(7));
                Task removed = duke.tasks.deleteTask(number);
                return duke.ui.printDeleteTaskMessage(removed) + duke.ui.printNumberOfTasksMessage(duke.tasks);
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            } catch (StringIndexOutOfBoundsException e) {
                return duke.ui.printMissingNumberAfterCommandError("delete");
            }
        } else if (input.startsWith("find ")) {
            try {
                String keyWord = input.substring(5);
                TaskList tempList = duke.tasks.find(keyWord);
                return duke.ui.printTaskList(tempList);
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            }
        } else {
            return duke.ui.printInvalidCommandError();
        }
    }

}

