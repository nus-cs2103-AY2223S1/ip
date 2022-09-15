package duke.commands;

import javafx.scene.Node;

/**
 * Represents the result of an executed command.
 *
 * @author sikai00
 */
public class CommandResult {
    private final String msg;
    private final Node node;

    /**
     * Initializes a new CommandResult instance.
     * @param msg Response message from the executed Command.
     */
    public CommandResult(String msg) {
        this.msg = msg;
        this.node = null;
    }

    /**
     * Initializes a new CommandResult instance with a Node.
     * @param msg Response message from the executed Command.
     */
    public CommandResult(String msg, Node node) {
        this.msg = msg;
        this.node = node;
    }

    public String getMessage() {
        return this.msg;
    }

    public Node getNode() {
        return this.node;
    }

    public boolean hasNode() {
        return this.node != null;
    }
}
