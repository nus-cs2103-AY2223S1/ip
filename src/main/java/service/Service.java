package service;

import data.IStorage;
import entities.Task;
import entities.Tasklist;

public class Service {
    public Tasklist list;
    private IStorage storage;
    public Ui ui;

    public Service(IStorage storage, Ui ui) {
        this.storage = storage;
        this.list = storage.load();
        this.ui = ui;
    }

    public void updateStorage() {
        this.storage.write(this.list);
    }

    public void addToList(Task t) {
        int size;
        if ((size = this.list.size()) == 1) {
            this.ui.customPrint(String.format("Got it. I've added this task:\n  " +
                    t +
                    "\nNow you have %d task in the list.", size));
        } else {
            this.ui.customPrint(String.format("Got it. I've added this task:\n  " +
                    t +
                    "\nNow you have %d tasks in the list.", size));
        }
    }
}
