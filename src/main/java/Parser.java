public class Parser {

    public Command parseText(String text) {
        String[] txtArr = text.split(" ");
        switch (txtArr[0]) {
        case "mark":
            return new MarkCommand(text, false);
        case "unmark":
            return new UnmarkCommand(text, false);
        case "list":
            return new ListCommand(text, false);
        case "todo":
            return new AddCommandToDo(text, false);
        case "deadline":
            return new AddCommandDeadLine(text, false);
        case "event":
            return new AddCommandEvent(text, false);
        case "delete":
            return new DeleteCommand(text, false);
        case "greet":
            return new GreetCommand(text, false);
        case "bye":
            return new ByeCommand(text, true);
        default:
            return new DefaultCommand(text, false);
        }
    }

}

