public class DeleteCommand extends Command {
    public static final boolean IS_EXIT = false;
    public final int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.getList().get(this.deleteIndex);
            taskList.getList().remove(this.deleteIndex);
            System.out.println("Noted. I've removed this task:\n " + ui.beautyWrapTask(removedTask) + "\nNow you have " + taskList.getList().size() + " tasks in the list.\n");

            /* * *
             *  Write file in duke.txt
             * * */
            String list = "";
            for (Task t : taskList.getList()) {
                list += t.toString();
            }
            storage.write(list);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("☹ OOPS!!! You did not specify which task to be delete.\n");
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("☹ OOPS!!! Your list only has " + taskList.getList().size() + " tasks.\n");
        }
    }

    public boolean isExit() {
        return this.IS_EXIT;
    }
}
