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
     */
    public SearchArgument(Input input) {
        super(input, "s", "Search argument should not be empty!",
                "This command needs a search argument! e.g /s book");
    }

    /**
     * Creates SearchArgument without input
     */
    public SearchArgument() {
        super("s", "Search argument should not be empty!",
                "This command needs a search argument! e.g /s book");
    }
    @Override
    public String getUsage() {
        return formatHelp("book", false);
    }
}
