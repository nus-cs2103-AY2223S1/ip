package kirby.commands;

import kirby.tasks.Event;
import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
import kirby.exceptions.KirbyMissingArgumentException;
import java.io.IOException;

public class EventCommand extends Command {
    private String inputString;
    public EventCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (!inputString.contains("/at") || inputString.length() - 1 < inputString.indexOf("/at") + 4 || inputString.indexOf(" /at") <= inputString.indexOf("event") + 6) {
            throw new KirbyMissingArgumentException("event");
        }
        String taskName = inputString.substring(inputString.indexOf("event") + 6, inputString.indexOf(" /at"));
        String at = inputString.substring(inputString.indexOf("/at") + 4);
        Event event = new Event(taskName, at);
        tasks.addTask(event);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
