package data;

import entities.Task;
import entities.Tasklist;
import exceptions.DukeException;

public interface IStorage {
    public void write(Tasklist task);
    public Tasklist load();
}
