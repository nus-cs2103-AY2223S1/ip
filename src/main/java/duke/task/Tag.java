package duke.task;

/**
 * Class encapsulating a tag that can be placed on a task.
 */
public class Tag {
    private String name;

    /**
     * Constructor of the tag class.
     *
     * @param name Name of tag.
     */
    public Tag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "#" + this.name;
    }

    public String getName() {
        return this.name;
    }
}
