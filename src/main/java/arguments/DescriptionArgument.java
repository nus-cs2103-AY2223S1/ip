package arguments;

import input.Input;

/**
 * Argument for description used by multiple commands
 */
public class DescriptionArgument extends StringArgument {
    /**
     * Creates a new DescriptionArgument object
     * @param input Input object
     */
    public DescriptionArgument(Input input) {
        super(input, "d", "The description should not be empty!",
                "This command needs a non-empty description.");
    }
}
