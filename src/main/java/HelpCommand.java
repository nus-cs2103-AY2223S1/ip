public class HelpCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] commands = new String[] {"help: prints the help menu",
                "list: prints out the todo list",
                "bye: closes the program",
                "mark: marks task as done",
                "unmark: marks task as not done",
                "todo: adds a todo task",
                "deadline: adds a task with a deadline",
                "event: adds an event"};

        for (int i = 0; i < commands.length; ++i) {
            System.out.printf("\t%s", commands[i]);
        }

        storage.save(taskList);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
