import java.util.HashMap;

public class DukeCommandMap {
    protected HashMap<String, DukeCommand> commandMap = new HashMap<>();

    public DukeCommandMap () {
        commandMap.put("list", new ListTasksCommand());
        commandMap.put("mark", new MarkAsDoneCommand());
        commandMap.put("unmark", new MarkAsUndoneCommand());
        commandMap.put("todo", new AddTodoCommand());
        commandMap.put("deadline", new AddDeadlineCommand());
        commandMap.put("event", new AddEventCommand());
        commandMap.put("delete", new AddTodoCommand());
    }

    public DukeCommand getCommand(String keyword) throws DukeException {
        if (!commandMap.containsKey(keyword)) {
            throw new DukeException("Command not found!\n");
        }
        return commandMap.get(keyword);
    }
}
