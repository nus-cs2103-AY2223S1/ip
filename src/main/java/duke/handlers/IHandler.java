package duke.handlers;

import duke.exceptions.DukeException;
import duke.service.Service;

public interface IHandler {
    /**
     * Handles the command
     * @param s Service object of the application
     * @throws DukeException
     */
    void handle(Service s) throws DukeException;
}
