package bobthebot.command;

import bobthebot.tasks.ToDoList;
import bobthebot.utils.LanguageBank;

/**
 * Constructs find command.
 */
public class FindCommand extends Command{
    private ToDoList list;
    private String keyword;

    /**
     * Constructs instance of find command
     */
    public FindCommand(ToDoList list, String keyword) {
        super("find");
        this.list = list;
        this.keyword = keyword;
    }

    /**
     *  Executes the find command by finding matching tasks and crafting a response.
     *
     * @return  String representing tasks which match the find keyword.
     */
    @Override
    public String execute() {
        String response = "";
        if (list.getLength() == 0) {
            response = LanguageBank.FIND_EMPTY_LIST_MESSAGE;
            return response;
        }

        ToDoList matchingTasks = list.findTasks(keyword);

        if (matchingTasks.getLength() == 0) {
            response = LanguageBank.FIND_NOTHING_FOUND_MESSAGE;
            return response;
        }

        response = LanguageBank.MATCHING_ITEMS_MESSAGE;
        response += matchingTasks.toString();
        return response;
    }
}
