public class GreetCommand extends Command {

    public GreetCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException{
        textBox.chat("Hello! I'm CleverNotBot\n What can I do for you?");
    }
}
