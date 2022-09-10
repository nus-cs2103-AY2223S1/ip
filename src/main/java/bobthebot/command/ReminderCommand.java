package bobthebot.command;

import bobthebot.tasks.ToDoList;
import bobthebot.utils.LanguageBank;
import bobthebot.utils.Ui;

public class ReminderCommand extends Command {
    private ToDoList list;

    public ReminderCommand(ToDoList list) {
        super("reminder");
        this.list = list;
    }

    @Override
    public String execute() {
        ToDoList tasksDueSoon = list.tasksDueSoon();
        String result = LanguageBank.UPCOMING_TASKS_MESSAGE;
        result += tasksDueSoon.toString();
        Ui.formatMessage(result);
        return result;
    }
}
