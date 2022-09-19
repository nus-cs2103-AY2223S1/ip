package task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an object that allows user to describe something they need to do.
 */
public class ToDo extends Task {

    private static final String SHORTHAND = "T";

    /**
     * Initialises the ToDo with the given description and no tags.
     *
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        this(description, new ArrayList<>());
    }

    /**
     * Initialises the ToDo with the given description and tags.
     *
     * @param description The description of the ToDo.
     * @param tags A list of tags of the ToDo.
     */
    public ToDo(String description, List<String> tags) {
        super(description, SHORTHAND, tags);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String tags = super.getPrintableTags();
        if (!tags.equals("")) {
            tags = "[" + tags + "]";
        }
        return String.format("%s %s", super.toString(), tags);
    }

}
