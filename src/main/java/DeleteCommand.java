public class DeleteCommand extends Command{
    public String index;
    public DeleteCommand(String index){
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            Integer i = Integer.parseInt(this.index);
            Task task = tasks.get(i-1);
            tasks.removeTask(this.index);

        } catch (IndexOutOfBoundsException e) {

            throw new InvalidInputException(this.index, "mark");
        }
    }
}
