public class UnmarkFunction extends Function {

    public UnmarkFunction(String function,boolean exit){
        super(function,exit);
    }

    @Override
    public void run(TaskList tasks,UITextBox textBox, Storage storage) throws CleverNotBotException{
        int number = Integer. parseInt(getFunction().split(" ")[1]) - 1; //mark 3 -> 3, because number 3 is actually idx 2
        Task taskToUnmark = tasks.getTask(number);
        if(taskToUnmark.checkMarked().equals("X")) {
            Task unmarkedTask = taskToUnmark.toggleCompleted();
            tasks.removeTask(taskToUnmark);
            tasks.addTaskByIdx(number, unmarkedTask);
            textBox.chat(String.format("OK, I've marked this task as not done yet:\n  [%s] %s",
                    unmarkedTask.checkMarked(),unmarkedTask.getName()));
        } else{
            textBox.chat("Hey! This task is already unmarked!");
        }

    }
}
