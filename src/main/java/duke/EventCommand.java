package duke;

import java.util.ArrayList;

public class EventCommand extends TaskCommand {
    @Override
    String addTaskToList(String fullCommand, ArrayList<Task> listOfTasks) {
        assert fullCommand.length() >= 5; 
        int index = fullCommand.indexOf("/");
        String time = fullCommand.substring(index + 4);
        String name = fullCommand.substring(6, index - 1);
        TaskList taskList = new TaskList(listOfTasks);
        Task t = new Event(name, false, time);
        return taskList.addToList(t);
    }

    @Override
    boolean isTaskEmpty(String fullCommand) {
        return fullCommand.length() == 5;
    }

    @Override
    void handleEmptyTask(String fullCommand) throws DukeEventEmptyException {
        if (isTaskEmpty(fullCommand)) {
            throw new DukeEventEmptyException();
        }
        assert fullCommand.length() > 5; 
    }
}