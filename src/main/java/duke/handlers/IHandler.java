package duke.handlers;

import duke.exceptions.DukeException;
import duke.service.Service;

public interface IHandler {
    void handle(Service list) throws DukeException;
}
