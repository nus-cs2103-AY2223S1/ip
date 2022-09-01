package clevernotbot;

public abstract class Command {
    private String commandName;
    private boolean exit;

    public Command(String commandName, boolean exit){
        this.commandName = commandName;
        this.exit = exit;
    }

    public String getCommandName(){
        return commandName;
    }

    public boolean isExitingProgram(){
        return exit;
    }

    public abstract void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException;
}
