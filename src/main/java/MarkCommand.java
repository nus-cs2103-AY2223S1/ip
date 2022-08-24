public class MarkCommand extends Command {
    public static final boolean IS_EXIT = false;
    public final int markIndex;
    public final String command;

    public MarkCommand(String command, int markIndex) {
        this.markIndex = markIndex;
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (command.equals("mark")) {
                Task markedTask = taskList.getList().get(this.markIndex).mark();
                taskList.getList().set(this.markIndex, markedTask);
                System.out.println("Nice! I've marked this task as done:\n" + ui.beautyWrapTask(markedTask) + "\n");
                /* * *
                 *  Write file in duke.txt
                 * * */
                String list = "";
                for (Task t : taskList.getList()) {
                    list += t.toString();
                }
                storage.write(list);

            } else if (command.equals("unmark")) {
                Task unmarkedTask = taskList.getList().get(this.markIndex).unmark();
                taskList.getList().set(this.markIndex, unmarkedTask);
                System.out.println("OK, I've marked this task as not done yet:\n" + ui.beautyWrapTask(unmarkedTask) + "\n");
                /* * *
                 *  Write file in duke.txt
                 * * */
                String list = "";
                for (Task t : taskList.getList()) {
                    list += t.toString();
                }
                storage.write(list);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("☹ OOPS!!! You did not specify which task to be marked/unmarked.\n");
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("☹ OOPS!!! Your list only has " + taskList.getList().size() + " tasks.\n");
        }
    }

    public boolean isExit() {
        return this.IS_EXIT;
    }
}
