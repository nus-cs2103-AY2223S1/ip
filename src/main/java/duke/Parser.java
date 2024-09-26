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
        String[] commands = input.split(" ");
        int numOfCommands = commands.length;
        String output = "";
        switch (commands[0]) {
        case "list":
            output = duke.ui.printTaskList(duke.tasks);
            break;
        case "mass":
            if (commands[2].equals("ALL")) {
                switch (commands[1]) {
                case "mark":
                    duke.tasks.markAll();
                    output = duke.ui.printMarkAllTaskMessage("mark");
                    break;
                case "unmark":
                    duke.tasks.unMarkAll();
                    output = duke.ui.printMarkAllTaskMessage("unmark");
                    break;
                case "delete":
                    output = duke.ui.printRemoveAllTasksMessage(duke.tasks);
                    duke.tasks.deleteAll();
                    break;
                default:
                    return duke.ui.printInvalidCommandError();
                }
            } else {
                for (int i = numOfCommands - 1; i > 1; i--) {
                    output += parse(commands[1] + " " + commands[i]) + "\n";
                }
            }
            break;
        case "mark":
            try {
                int number = Integer.parseInt(commands[1]);
                duke.tasks.mark(number);
                output = duke.ui.printMarkTaskMessage("mark", duke.tasks.getTask(number - 1));
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            } catch (Exception e) {
                return duke.ui.printMissingNumberAfterCommandError("mark");
            }
            break;
        case "unmark":
            try {
                int number = Integer.parseInt(commands[1]);
                duke.tasks.unMark(number);
                output = duke.ui.printMarkTaskMessage("unmark", duke.tasks.getTask(number - 1));
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            } catch (Exception e) {
                return duke.ui.printMissingNumberAfterCommandError("unmark");
            }
            break;
        case "todo":
            StringBuilder todoDescription = new StringBuilder();
            for (int i = 1; i < numOfCommands; i++) {
                todoDescription.append(commands[i]).append(" ");
            }
            if (todoDescription.toString().equals("")) {
                return duke.ui.printSomethingMissingError(commands[0]);
            }
            ToDo toDo = new ToDo(todoDescription.toString());
            duke.tasks.addTask(toDo);
            output = duke.ui.printAddTaskMessage(toDo) + duke.ui.printNumberOfTasksMessage(duke.tasks);
            break;
        case "deadline":
            StringBuilder deadlineDescription = new StringBuilder();
            for (int i = 1; i <= numOfCommands - 3; i++) {
                deadlineDescription.append(commands[i]).append(" ");
            }
            if (!commands[numOfCommands - 2].equals("/by")
                    || deadlineDescription.toString().equals("") || commands[numOfCommands - 1].equals("")) {
                return duke.ui.printSomethingMissingError(commands[0]);
            }
            Deadline deadline = new Deadline(deadlineDescription.toString(), commands[numOfCommands - 1]);
            duke.tasks.addTask(deadline);
            output = duke.ui.printAddTaskMessage(deadline) + duke.ui.printNumberOfTasksMessage(duke.tasks);
            break;
        case "event":
            StringBuilder description = new StringBuilder();
            for (int i = 1; i <= numOfCommands - 3; i++) {
                description.append(commands[i]).append(" ");
            }
            if (!commands[numOfCommands - 2].equals("/at")
                    || description.toString().equals("") || commands[numOfCommands - 1].equals("")) {
                return duke.ui.printSomethingMissingError(commands[0]);
            }
            Event event = new Event(description.toString(), commands[numOfCommands - 1]);
            duke.tasks.addTask(event);
            output = duke.ui.printAddTaskMessage(event) + duke.ui.printNumberOfTasksMessage(duke.tasks);
            break;
        case "fixed":
            StringBuilder fixedTaskDescription = new StringBuilder();
            for (int i = 1; i <= numOfCommands - 3; i++) {
                fixedTaskDescription.append(commands[i]).append(" ");
            }
            if (!commands[numOfCommands - 2].equals("/takes")
                    || fixedTaskDescription.toString().equals("") || commands[numOfCommands - 1].equals("")) {
                return duke.ui.printSomethingMissingError(commands[0]);
            }
            FixedDurationTask fixedDurationTask = new FixedDurationTask(fixedTaskDescription.toString(),
                    Integer.parseInt(commands[numOfCommands - 1]));
            duke.tasks.addTask(fixedDurationTask);
            output = duke.ui.printAddTaskMessage(fixedDurationTask) + duke.ui.printNumberOfTasksMessage(duke.tasks);
            break;
        case "delete":
            try {
                int number = Integer.parseInt(input.substring(7));
                Task removed = duke.tasks.deleteTask(number);
                output = duke.ui.printDeleteTaskMessage(removed) + duke.ui.printNumberOfTasksMessage(duke.tasks);
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            } catch (StringIndexOutOfBoundsException e) {
                return duke.ui.printMissingNumberAfterCommandError("delete");
            }
            break;
        case "find":
            try {
                String keyWord = input.substring(5);
                TaskList tempList = duke.tasks.find(keyWord);
                output = duke.ui.printTaskList(tempList);
            } catch (DukeException e) {
                return duke.ui.printNoSuchTaskError();
            }
            break;
        case "bye":
            System.exit(0);
            break;
        default:
            return duke.ui.printInvalidCommandError();
        }
        return output;

    }
}




