package jduke.commands;

import jduke.data.TaskList;

public abstract class Command {
    protected TaskList tasklist;

    public void setData(TaskList tasklist) {
        this.tasklist = tasklist;
    }
    public abstract String execute();
}
