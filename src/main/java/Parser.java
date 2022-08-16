public class Parser {
    public static Command parse(String input) {
        input = input.strip();
        String inputCommand = input.indexOf(" ") == -1 ?
                input.toLowerCase() : input.toLowerCase().substring(0, input.indexOf(" "));
        String[] commandAndArgs = input.split(" ", 2);
        String[] args = new String[0];
        if (commandAndArgs.length > 1) {
            args = commandAndArgs[1].split("\\s*/..\\s*", 2);
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].strip();
            }
        }

        switch (inputCommand) {
            case "help":
                return new Command.HelpCommand(args);
            case "bye":
                return new Command.ByeCommand(args);
            case "list":
                return new Command.ListCommand(args);
            case "mark":
                return new Command.MarkCommand(args);
            case "unmark":
                return new Command.UnmarkCommand(args);
            case "todo":
                return new Command.ToDoCommand(args);
            case "event":
                return new Command.EventCommand(args);
            case "deadline":
                return new Command.DeadlineCommand(args);
            case "":
                return new Command.EmptyCommand(args);
            default:
                return new Command.UnknownCommand(args);
        }
    }
}
