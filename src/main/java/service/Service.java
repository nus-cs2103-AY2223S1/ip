package service;

import data.IStorage;
import entities.Task;
import entities.Tasklist;

public class Service {
    public Tasklist list;
    private IStorage storage;

    public Service(IStorage storage) {
        this.storage = storage;
        this.list = storage.load();
    }

    public void updateStorage() {
        this.storage.write(this.list);
    }
}
