public class GreetFunction extends Function {

    public GreetFunction(String function,boolean exit){
        super(function,exit);
    }

    @Override
    public void run(TaskList tasks,UITextBox textBox, Storage storage) throws CleverNotBotException{
        textBox.chat("Hello! I'm CleverNotBot\n What can I do for you?");
    }
}
