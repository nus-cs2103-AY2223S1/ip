package cleverNotBot;

public class MarkCommand extends Command {

    public MarkCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        int number = Integer. parseInt(getCommandName().split(" ")[1]) - 1; //mark 3 -> 3, because number 3 is actually idx 2
        Task taskToMark = tasks.getTask(number);
        if(taskToMark.checkMarked().equals(" ")) {
            Task markedTask = taskToMark.toggleCompleted();
            tasks.removeTask(taskToMark);
            tasks.addTaskByIdx(number, markedTask);
            textBox.chat(String.format("Nice! I've marked this task as done:\n  [%s] %s",
                    markedTask.checkMarked(),markedTask.getName()));
        } else{
            textBox.chat("Hey! This task is already marked!");
        }

    }
}
