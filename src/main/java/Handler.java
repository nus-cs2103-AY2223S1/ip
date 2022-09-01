public class Handler {

    public Function parseText(String text) {
        String[] txtArr = text.split(" ");
        switch (txtArr[0]) {
        case "mark":
            return new MarkFunction(text, false);
        case "unmark":
            return new UnmarkFunction(text, false);
        case "list":
            return new ListFunction(text, false);
        case "todo":
            return new AddFunctionToDo(text, false);
        case "deadline":
            return new AddFunctionDeadLine(text, false);
        case "event":
            return new AddFunctionEvent(text, false);
        case "delete":
            return new DeleteFunction(text, false);
        case "greet":
            return new GreetFunction(text, false);
        case "bye":
            return new ByeFunction(text, true);
        default:
            return new DefaultFunction(text, false);
        }
    }

}

