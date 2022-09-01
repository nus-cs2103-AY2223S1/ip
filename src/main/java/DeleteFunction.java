public class DeleteFunction extends Function{

    public DeleteFunction(String function,boolean exit){
        super(function,exit);
    }

    public void run(TaskList tasks,UITextBox textBox, Storage storage) throws CleverNotBotException {
        try{
            String[] desc = getFunction().split(" ");
            if(desc.length <= 1){
                throw new CleverNotBotException("Invalid arguments. Please enter a number!",textBox);
            }
            Task deletedTask = tasks.getTask(Integer.parseInt(desc[1]) - 1); // Task 3 is in idx 2
            tasks.removeTask(deletedTask);
            storage.writeToFile(tasks.getTaskList());
            textBox.chat(String.format(
                         "Noted. I've removed this task:"+
                         "\n  %s"+
                         "\nNow you have %d tasks in the list."
                    ,deletedTask.toString(),tasks.getSize()));
        } catch (IndexOutOfBoundsException e){
            throw new CleverNotBotException("The task does not exist!",textBox);
        }

    }
}
