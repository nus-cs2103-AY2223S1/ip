package pikachu.command;

import pikachu.Pikachu;
import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.Ui;
import pikachu.task.Task;
import pikachu.TaskList;

public class UnmarkCommand extends Command {
    String input;

    public UnmarkCommand(String fullCommand) {
        this.input = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        if (!Pikachu.isNumeric(input.substring(7))) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (Integer.parseInt(input.substring(7)) > tasks.taskList.size() || Integer.parseInt(input.substring(7)) <= 0) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(7));
            Task task = tasks.taskList.get(temp - 1);
            task.setDone(false);
            System.out.println("Pipi-ka(Undone): " + task);
        }
        storage.save(tasks.taskList);
    }

    public boolean isExit() {
        return false;
    }
    
}
