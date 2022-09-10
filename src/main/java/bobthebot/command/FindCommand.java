package bobthebot.command;

import bobthebot.tasks.ToDoList;
import bobthebot.utils.LanguageBank;
import bobthebot.utils.Ui;

public class FindCommand extends Command{
    private ToDoList list;
    private String keyword;

    public FindCommand(ToDoList list, String keyword) {
        super("find");
        this.list = list;
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        ToDoList matchingTasks = list.findTasks(keyword);
        String result = LanguageBank.MATCHING_ITEMS_MESSAGE;
        result += matchingTasks.toString();
        Ui.formatMessage(result);
        return result;
    }
}
