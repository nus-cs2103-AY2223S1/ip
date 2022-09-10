package bobthebot.command;

import bobthebot.tasks.ToDoList;
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
        String result = "\tHere are the matching items on your list: \n";
        result += matchingTasks.toString();
        Ui.formatMessage(result);
        return result;
    }
}
