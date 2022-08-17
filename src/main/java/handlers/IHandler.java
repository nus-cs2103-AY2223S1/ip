package handlers;

import entities.Task;
import exceptions.DukeException;

import java.util.List;

public interface IHandler {
    void handle(List<Task> list) throws DukeException;
}
