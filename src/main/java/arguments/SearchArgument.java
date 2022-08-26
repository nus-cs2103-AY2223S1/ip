package arguments;

import input.Input;

/**
 * Class for SearchArgument
 */
public class SearchArgument extends StringArgument {
    /**
     * Creates new StringArgument with necessary parameters
     *
     * @param input          Input object
     * @param argumentName   Name for argument
     * @param emptyMessage   Message to show if argument is empty
     * @param missingMessage Message to show if argument is missing
     */
    public SearchArgument(Input input) {
        super(input, "s", "Search argument should not be empty!",
                "This command needs a search argument! e.g /s book");
    }


}
