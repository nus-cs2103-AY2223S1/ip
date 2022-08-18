/**
 * Generate Command base on the inputs from the console.
 */
public class CommandGenerator {
    private String command;
    private String commandAction;

    /**
     * Constructor.
     * Remove whitespaces from the front and back if any
     * Let the first word be the command
     * second word be commandAction, if any
     * @param text
     */
    public CommandGenerator(String text) {
        text = text.strip();
        command = text.split(" ",2)[0];
        if (text.split(" ",2).length > 1) {
            commandAction = text.split(" ",2)[1];
        }
    }

    /**
     * Retrieve command
     * @return command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Retrieve commandAction
     * @return commandAction
     */
    public String getCommandAction() {
        return commandAction;
    }

    /**
     * Check is the user input is an integer
     * @param commandAction
     * @return true if it is an integer else false
     */
    public static boolean isInteger(String commandAction) {
        try {
            Integer.parseInt(commandAction);
            return true;
        } catch (NumberFormatException e){
            IOHelper.print("Expected an Integer");
            return false;
        }
    }

}