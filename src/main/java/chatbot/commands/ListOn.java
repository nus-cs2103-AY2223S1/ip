package chatbot.commands;

import java.time.LocalDate;

import chatbot.tasks.TaskList;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which lists
 * all the tasks in the todo list.
 */
public class ListOn implements Command {
    private LocalDate date;

    public ListOn(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        ui.listTaskOn(todos.getTaskOn(date));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
