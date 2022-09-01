public class DefaultFunction extends Function{

    public DefaultFunction(String function,boolean exit){
        super(function,exit);
    }

    @Override
    public void run(TaskList tasks,UITextBox textBox, Storage storage) throws CleverNotBotException{
        textBox.chat("This command doesn't exist!");
    }
}
