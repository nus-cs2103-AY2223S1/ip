package arguments;

import input.Input;

public class DescriptionArgument extends StringArgument {
    public DescriptionArgument(Input input) {
        super(input, "d", "The description should not be empty!",
                "This command needs a non-empty description.");
    }
}
