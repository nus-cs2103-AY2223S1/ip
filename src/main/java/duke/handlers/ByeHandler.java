package duke.handlers;

import duke.service.Service;

/** Handles the user action for exiting the application */
public class ByeHandler implements IHandler {
    /**
     * Handles the "mark" command which marks a task as done.
     *
     * @param s Service object of the application
     */
    @Override
    public String handle(Service s) {
        return "Bye. Hope to see you again soon!";
    }
}
