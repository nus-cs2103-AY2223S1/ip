package Duke.Commands;

import Duke.Tasks.TaskList;

/**
 * Class that denotes the command of offering help and hints.
 */
public class HelpCommand extends UserCommand {
    private static final String HELP =
                "list                                               --list all tasks in your list\n" +
                "todo <task description>                            --add a \"todo\" task\n" +
                "deadline <task description> /<YYYY-MM-DD> [HH:MM]  --add a \"deadline\" task (HH:MM is optional) \n" +
                "event <task_description> /<YYYY-MM-DD> <HH:MM>     --add a \"event\" task (HH:MM is compulsory) \n" +
                "done <i>                                           --mark the ith task as done\n" +
                "\n" +
                "delete <i>                                         --delete the ith task (1-based)\n" +
                "find <keyword>                                     --find task(s) containing the keyword\n" +
                "sort                                               --sort all the tasks based on emergency(e.g. time)\n" +
                "sort Deadline                                      --sort all the deadlines based on emergency(e.g. time)\n" +
                "bye                                                --quit and save all your tasks";
    @Override
    public String execute(){
        return HELP ;
    }
}
