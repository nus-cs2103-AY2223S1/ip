package arguments;

import input.Input;

public class ByArgument extends StringArgument {
    public ByArgument(Input input) {
        super(input, "by",  "The by date should not be empty!", "This command needs a non-empty by argument.");
    }
}