package duke.handlers;

import duke.exceptions.DukeException;
import duke.service.Service;

/**
 * Handler to handle commands that are unknown.
 */
public class UnknownHandler implements IHandler {
    @Override
    public String handle(Service s) throws DukeException {
        throw new DukeException("Unknown command! Please try again.");
    }
}

