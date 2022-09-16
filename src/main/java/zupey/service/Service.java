package zupey.service;

import zupey.data.IStorage;
import zupey.entities.Task;
import zupey.entities.Tasklist;
import zupey.exceptions.ZupeyException;

/** Service object stores all the dependencies needed for the application */
public class Service {
    private IStorage storage;
    private Ui ui;
    private Tasklist list;
    private Tasklist previousList;

    /**
     * Constructs a new Service object.
     * @param storage IStorage for persistence
     * @param ui Ui for zupey
     */
    public Service(IStorage storage, Ui ui) {
        this.storage = storage;
        this.list = storage.load();
        this.ui = ui;
    }

    /**
     * Save the current state to persistent storage.
     */
    public void updateStorage() {
        this.storage.write(this.list);
    }

    /**
     * Reverts the Tasklist to the previous state
     *
     * @throws ZupeyException
     */
    public void undo() throws ZupeyException {
        if (this.previousList == null) {
            throw new ZupeyException("No previous command to undo!");
        }
        this.list = this.previousList;
        this.previousList = null;
    }
    /**
     * Saves the current state to previous Tasklist.
     */
    public void saveTasks() {
        this.previousList = this.list.copy();
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
