package duke.handlers;

import duke.enums.Command;
import duke.exceptions.DukeException;
import duke.service.Service;


public class HandlerFactory {
    String command;
    String taskName;
    String flag;
    String flagOption;

    private static class UnknownHandler implements IHandler {
        @Override
        public void handle(Service s) throws DukeException {
            throw new DukeException("Unknown command! Please try again.");
        }
    }

    public HandlerFactory(String command) {
        this.command = command;
    }

    public HandlerFactory taskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    public HandlerFactory flag(String flag) {
        this.flag = flag;
        return this;
    }

    public HandlerFactory flagOption(String option) {
        this.flagOption = option;
        return this;
    }

    /**
     * Returns a Hander based on the command input from the user
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
}
