package pikachu.command;

import pikachu.Pikachu;
import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.Ui;
import pikachu.task.Task;
import pikachu.TaskList;

public class DeleteCommand extends Command {
    String input;

    public DeleteCommand(String fullCommand) {
        this.input = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException{
        if (!Pikachu.isNumeric(input.substring(7))) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (Integer.parseInt(input.substring(7)) > tasks.taskList.size() || Integer.parseInt(input.substring(7)) <= 0) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(7));
            Task task = tasks.taskList.get(temp - 1);
            tasks.taskList.remove(temp-1);
            System.out.println("Pi-ka(Removed): " + task + '\n' +"Pikaaaaa: " + tasks.taskList.size() + (tasks.taskList.size() > 1 ? " tasks" : " task"));
            storage.save(tasks.taskList);
        }
    }

    public boolean isExit() {
        return false;
    }
    
}
