package duke.handlers;

import duke.enums.Command;
import duke.exceptions.DukeException;
import duke.service.Service;


/**
 * Creates different Handlers based on user-input from Parser
 */
public class HandlerFactory {
    private String command;
    private String taskName;
    private String flag;
    private String flagOption;

    private static class UnknownHandler implements IHandler {
        @Override
        public void handle(Service s) throws DukeException {
            throw new DukeException("Unknown command! Please try again.");
        }
    }

    public HandlerFactory(String command) {
        this.command = command;
    }

    /**
     * Returns the same HandlerFactory with different task name.
     * @param taskName
     * @return HandlerFactory with same fields but different taskName.
     */
    public HandlerFactory taskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    /**
     * Returns the same HandlerFactory with different flag
     * @param flag
     * @return HandlerFactory with same fields but different flag.
     */
    public HandlerFactory flag(String flag) {
        this.flag = flag;
        return this;
    }

    /**
     * Returns the same HandlerFactory with different flagOption
     * @param option
     * @return HandlerFactory with same fields but different flagOption.
     */
    public HandlerFactory flagOption(String option) {
        this.flagOption = option;
        return this;
    }

    /**
     * Returns a Handler based on the command input from the user
     *
     * @return IHandler
     */
    public IHandler build() {
        try {
            Command commandEnum = Command.valueOf(this.command.toUpperCase());
            switch (commandEnum) {
            case TODO:
                return new TodoHandler(this);
            case EVENT:
                return new EventHandler(this);
            case DEADLINE:
                return new DeadlineHandler(this);
            case LIST:
                return new ListHandler();
            case MARK:
                return new MarkHandler(this);
            case UNMARK:
                return new UnmarkHandler(this);
            case DELETE:
                return new DeleteHandler(this);
            case FIND:
                return new FindHandler(this);
            default:
                return new UnknownHandler();
            }
        } catch (IllegalArgumentException ex) {
            return new UnknownHandler();
        }
    }

    public String getCommand() {
        return this.command;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getFlag() {
        return this.flag;
    }

    public String getFlagOption() {
        return this.flagOption;
    }
}
