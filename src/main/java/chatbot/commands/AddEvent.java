package chatbot.commands;

import chatbot.main.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

import java.time.LocalDate;

public class AddEvent implements Command {
    public String taskName;
    public LocalDate date;

    public AddEvent(String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        Task event = todos.addEvent(this.taskName, this.date);
        ui.add(event, todos.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
