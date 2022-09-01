public class ByeCommand extends Command {

    public ByeCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException{
        textBox.chat("Bye. Hope to see you again soon!");
    }
}
