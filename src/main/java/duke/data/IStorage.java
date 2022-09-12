package duke.data;

import duke.entities.Tasklist;

/** Interface representing persistant storage */
public interface IStorage {
    public void write(Tasklist task);
    public Tasklist load();
}
