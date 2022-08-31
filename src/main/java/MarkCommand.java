public class MarkCommand extends Command {
    String input;

    MarkCommand(String fullCommand) {
        this.input = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        if (!Pikachu.isNumeric(input.substring(5))) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (Integer.parseInt(input.substring(5)) > tasks.taskList.size() || Integer.parseInt(input.substring(5)) <= 0) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(5));
            Task task = tasks.taskList.get(temp - 1);
            task.setDone(true);
            System.out.println("Pi-ka(Done): " + task);
        }
        storage.save(tasks.taskList);
    }

    public boolean isExit() {
        return false;
    }
    
}
