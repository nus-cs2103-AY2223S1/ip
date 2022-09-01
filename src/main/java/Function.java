public abstract class Function {
    private String function;
    private boolean exit;

    public Function(String function,boolean exit){
        this.function = function;
        this.exit = exit;
    }

    public String getFunction(){
        return function;
    }

    public boolean isExitingProgram(){
        return exit;
    }

    public abstract void run(TaskList tasks,UITextBox textBox,Storage storage) throws CleverNotBotException;
}
