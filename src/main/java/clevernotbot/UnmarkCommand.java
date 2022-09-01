package clevernotbot;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        int number = Integer. parseInt(getCommandName().split(" ")[1]) - 1; //mark 3 -> 3, because number 3 is actually idx 2
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
