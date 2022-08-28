package unc.command;

import unc.*;
import unc.task.Event;

public class EventCommand extends Command{
    private final String description;

    public EventCommand(String input) {
        this.description = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws UncException {
        String[] phrases = description.split(" /at ", 2);
        Event newEvent = new Event(phrases[0], phrases[1]);
        taskList.add(newEvent);
        ui.addEvent(taskList, newEvent);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
