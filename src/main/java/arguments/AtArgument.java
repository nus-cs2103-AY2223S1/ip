package arguments;

import input.Input;

public class AtArgument extends StringArgument {
    public AtArgument(Input input) {
        super(input, "at", "The at date should not be empty!",
                "This command needs an at date");
    }
}
