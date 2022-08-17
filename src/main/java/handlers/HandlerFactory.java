package handlers;

import enums.Command;
import exceptions.DukeException;

import java.util.List;


public class HandlerFactory {
    String command;
    String taskName;
    String flag;
    String flagOption;

    private static class UnknownHandler implements IHandler {
        @Override
        public void handle(List list) throws DukeException {
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

    public IHandler build() {
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
            default:
                return new UnknownHandler();
        }
    }
}
