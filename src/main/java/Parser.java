public class Parser {

    public static Command parse(String commandStr) {

        String[] commandStrArr = commandStr.split(" ");
        String commandMain = commandStrArr[0];

        switch (commandMain) {
        case "bye":
            return new CommandExit();
        case "todo":
            return makeTodo();
        case "deadline":
            return makeDeadline();
        case "event":
            return makeEvent();
        case "list":
            return new CommandListAllTasks();
        case "mark": {
            int index = Integer.parseInt(commandStrArr[1]);
            return new CommandMarkTask(index);
        }
        case "unmark": {
            int index = Integer.parseInt(commandStrArr[1]);
            return new CommandUnmarkTask(index);
        }
        case "delete": {
            int index = Integer.parseInt(commandStrArr[1]);
            return new CommandRemoveTask(index);
        }
        default:
            return new CommandUnknown();
        }

    }

}
