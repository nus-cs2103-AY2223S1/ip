package handlers;

import exceptions.DukeException;
import service.Service;

public interface IHandler {
    void handle(Service list) throws DukeException;
}
