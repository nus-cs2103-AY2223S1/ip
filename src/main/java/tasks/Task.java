package tasks;

import exceptions.DukeException;
import utils.CheckBox;

public abstract class Task {
    private final String description;
    private final CheckBox checkBox;

    public Task(String name) {
        this.description = name;
        this.checkBox = new CheckBox(false);
    }

    public void mark(boolean isCompleted) {
        this.checkBox.setCheckBox(isCompleted);
    }

    public abstract void checkCommandValidity(String value, String flag, String options) throws DukeException;

    @Override
    public String toString() {
        return this.checkBox + " " + this.description;
    }
}
