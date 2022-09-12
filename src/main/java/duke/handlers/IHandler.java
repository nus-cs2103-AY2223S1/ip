package duke.handlers;

import duke.exceptions.DukeException;
import duke.service.Service;

/**
 * Interface representing a Handler.
 */
public interface IHandler {
    /**
     * Handles the command
     * @param s Service object of the application
     * @throws DukeException
     */
    String handle(Service s) throws DukeException;
}
