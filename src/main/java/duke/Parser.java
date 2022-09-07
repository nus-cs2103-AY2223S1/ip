package duke;

/**
 * duke.Parser class helps to interpret command Strings and returns a duke.Command object with type of command user wants
 * @author Shaune Ang
 */
public class Parser {
    /**
     * Returns a duke.Command object based on the command given by user input
     * @param command User Input
     * @return duke.Command object with type of command user wants
     * @throws Exception
     */
    public static Command parse(String command) throws Exception {
        if (command.equals("list")) {
            return new Command(Command.CommandTypes.LIST, command);
        }
        else if (command.startsWith("todo")) { // create todos
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
        else if (command.startsWith("find")) { // find
            return new Command(Command.CommandTypes.FIND, command);
        }
        else {
            throw new Exception("I am sorry, I do not comprehend such commands. Please Try again...");
        }
    }
}
