package duke;

public class Parser {

    public static void parseInput(String input, TaskList taskList) throws PloopyException {
        String[] inputSequence = input.split(" ");
        if (input.isBlank() || input.isEmpty()) {
            throw new PloopyException("blank");
        }
        String command = inputSequence[0];
        switch (command) {
            case "mark":
                if (!isEmptyCommand(input, "mark".length())) {
                    taskList.markTask(Integer.parseInt(inputSequence[1]));
                } else {
                    throw new PloopyException("mark");
                }
                break;
            case "unmark":
                if (!isEmptyCommand(input, "unmark".length())) {
                    taskList.unmarkTask(Integer.parseInt(inputSequence[1]));
                } else {
                    throw new PloopyException("unmark");
                }
                break;
            case "list":
                taskList.displayList();
                break;
            case "delete":
                if (!isEmptyCommand(input, "delete".length())) {
                    taskList.deleteTask(Integer.parseInt(inputSequence[1]));
                } else {
                    throw new PloopyException("delete");
                }
                break;
            case "todo":
                if (!isEmptyCommand(input, "todo".length())) {
                    taskList.createToDo(input);
                } else {
                    throw new PloopyException("todo");
                }
                break;
            case "deadline":
                if (!isEmptyCommand(input, "deadline".length())) {
                    taskList.createDeadline(input);
                } else {
                    throw new PloopyException("deadline");
                }
                break;
            case "event":
                if (!isEmptyCommand(input, "mark".length())) {
                    taskList.createEvent(input);
                } else {
                    throw new PloopyException("event");
                }
                break;
            default:
                throw new PloopyException("nonsense");
        }

    }


    private static boolean isEmptyCommand(String command, int size) {
        return command.trim().length() == size;
    }
}
