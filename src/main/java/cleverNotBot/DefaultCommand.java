package cleverNotBot;

public class DefaultCommand extends Command {

    public DefaultCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        textBox.chat("This command doesn't exist!");
    }
}
