public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList taskList,
                        Storage storage, Ui ui) {
        try {
            if (index > taskList.length() || index < 1) {
                //handles index out of bounds exception
                throw new RoofusException("Hey! It's not even in this list!");
            }
        } catch (RoofusException err){
            ui.errMessage(err.getMessage());
        }
        taskList.delete(index);
        ui.delete(taskList.getTask(index - 1).toString(), 
                taskList.length());
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
