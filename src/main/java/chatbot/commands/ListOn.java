package chatbot.commands;

import chatbot.main.UI;
import chatbot.tasks.TaskList;

import java.time.LocalDate;

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
