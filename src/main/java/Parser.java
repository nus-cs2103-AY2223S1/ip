public class Parser {
    public Parser() {
    }

    public static Command parse(String input) throws IanaException {
        String[] taskArray = input.split(" ", 2);
        String action = taskArray[0];

        try {
            switch(Actions.valueOf(action)) {
                case bye: 
                return new ExitCommand();

                case list:
                return new ListCommand();

                case delete:
                return new DeleteCommand(taskArray[1]);

                case mark:
                return new MarkCommand(taskArray[1]);

                case unmark:
                return new UnmarkCommand(taskArray[1]);

                case todo:

                case event:

                case deadline:
                return new AddTaskCommand(input);

                default:
                return new ExitCommand();
            }
        } catch (IllegalArgumentException e) {
            throw new IanaException("Oops, this action is invalid!! :C");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IanaException("Oops, this action is invalid!! :C");
        }
    }
}