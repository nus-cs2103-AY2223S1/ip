/**
 * Generate Command base on the inputs from the console.
 */
public class CommandGenerator {
    private String command;
    private String commandAction;
    private String text;

    /**
     * Constructor.
     * Remove whitespaces from the front and back if any
     * Let the first word be the command
     * second word be commandAction, if any
     * @param text
     */
    public CommandGenerator(String text) {
        this.text = text.strip();
        command = this.text.split(" ",2)[0];
        if (this.text.split(" ",2).length > 1) {
            commandAction = this.text.split(" ",2)[1];
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
     * Retrieve text
     * @return text
     */
    public String getText() {
        return text;
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