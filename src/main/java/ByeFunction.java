public class ByeFunction extends Function {

    public ByeFunction(String function,boolean exit){
        super(function,exit);
    }

    @Override
    public void run(TaskList tasks,UITextBox textBox) throws CleverNotBotException{
        textBox.chat("Bye. Hope to see you again soon!");
    }
}
