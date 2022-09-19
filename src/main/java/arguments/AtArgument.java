package arguments;

import input.Input;

/**
 * Argument for 'at' to be used by event command
 */
public class AtArgument extends StringArgument {
    /**
     * Creates new AtArgument object
     */
    public AtArgument() {
        super("at", "The at date should not be empty!",
                "This command needs an at date");
    }

    /**
     * Creates new AtArgument object
     * @param input Input object representing CLI input
     */
    public AtArgument(Input input) {
        super(input, "at", "The at date should not be empty!",
                "This command needs an at date");
    }
    @Override
    public String getUsage() {
        return formatHelp("next Monday", false);
    }
}
