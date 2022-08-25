// Helps to interpret command Strings and returns a Command object
public class Parser {
    public static Command parse(String command) throws Exception {
        if (command.equals("list")) {
            return new Command(Command.CommandTypes.LIST, command);
        }
        else if (command.startsWith("todo")) { // create todo
            return new Command(Command.CommandTypes.TODO, command);
        }
        else if (command.startsWith("deadline")) { // create deadline
            return new Command(Command.CommandTypes.DEADLINE, command);
        }
        else if(command.startsWith("event")) { // create event
            return new Command(Command.CommandTypes.EVENT, command);
        }
        else if (command.matches("mark [0-9]+") || command.matches("unmark [0-9]+")) { // mark
            if (command.startsWith("mark")) {
                return new Command(Command.CommandTypes.MARK, command);
            }
            else{
                return new Command(Command.CommandTypes.UNMARK, command);
            }
        }
        else if (command.matches("delete [0-9]+")) { // delete
            return new Command(Command.CommandTypes.DELETE, command);
        }
        else if (command.equals("bye")) {
            return new Command(Command.CommandTypes.EXIT, command);
        }
        else {
            throw new Exception("I am sorry, I do not comprehend such commands. Please Try again...");
        }
    }
}
