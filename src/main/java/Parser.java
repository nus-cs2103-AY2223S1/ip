public class Parser {

    public static void parseInput(String input, TaskList taskList) throws DukeException {
        String[] inputSequence = input.split(" ");
        if (input.isBlank() || input.isEmpty()) {
            throw new DukeException("blank");
        }
        String command = inputSequence[0];
        switch (command) {
            case "mark":
                if (!isEmptyCommand(input, "mark".length())) {
                    taskList.markTask(Integer.parseInt(inputSequence[1]));
                } else {
                    throw new DukeException("mark");
                }
                break;
            case "unmark":
                if (!isEmptyCommand(input, "unmark".length())) {
                    taskList.unmarkTask(Integer.parseInt(inputSequence[1]));
                } else {
                    throw new DukeException("unmark");
                }
                break;
            case "list":
                taskList.displayList();
                break;
            case "delete":
                if (!isEmptyCommand(input, "delete".length())) {
                    taskList.deleteTask(Integer.parseInt(inputSequence[1]));
                } else {
                    throw new DukeException("delete");
                }
                break;
            case "todo":
                if (!isEmptyCommand(input, "todo".length())) {
                    taskList.createToDo(input);
                } else {
                    throw new DukeException("todo");
                }
                break;
            case "deadline":
                if (!isEmptyCommand(input, "deadline".length())) {
                    taskList.createDeadline(input);
                } else {
                    throw new DukeException("deadline");
                }
                break;
            case "event":
                if (!isEmptyCommand(input, "mark".length())) {
                    taskList.createEvent(input);
                } else {
                    throw new DukeException("event");
                }
                break;
            default:
                throw new DukeException("nonsense");
        }

    }


    private static boolean isEmptyCommand(String command, int size) {
        return command.trim().length() == size;
    }
}
