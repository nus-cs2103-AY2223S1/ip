package duke.data;

import duke.entities.Tasklist;

public interface IStorage {
    public void write(Tasklist task);
    public Tasklist load();
}
