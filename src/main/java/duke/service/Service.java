package duke.service;

import duke.data.IStorage;
import duke.entities.Task;
import duke.entities.Tasklist;

/** Service object stores all the dependencies needed for the application */
public class Service {
    private Tasklist list;
    private IStorage storage;
    private Ui ui;

    /**
     * Constructs a new Service object.
     * @param storage IStorage for persistence
     * @param ui Ui for Duke
     */
    public Service(IStorage storage, Ui ui) {
        this.storage = storage;
        this.list = storage.load();
        this.ui = ui;
    }

    public void updateStorage() {
        this.storage.write(this.list);
    }

    /**
     * Adds a new Task to the Service's Tasklist
     *
     * @param t Task to be added
     */
    public void addToList(Task t) {
        this.list.add(t);
    }

    public Tasklist getList() {
        return this.list;
    }

    public void message(String s) {
        this.ui.receiveMessage(s);
    }
}
