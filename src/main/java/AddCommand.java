public class AddCommand extends Command {
    String[] str;
    Commands command;
    public enum Commands {
        DEADLINE,
        TODO,
        EVENT
    }
    public final Commands[] commands = new Commands[]{Commands.DEADLINE, Commands.TODO, Commands.EVENT};

    public AddCommand(String[] str, int i) {
        this.str = str;
        this.command = commands[i];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask = null;
        switch(command) {
        case DEADLINE:
            String[] dl = new String[2];
            try {
                dl = str[1].split(" /by ");
            } catch (Exception e) {
                ui.emptyDescription();
            }

            try {
                myTask = new Deadline(dl[0], dl[1]);
            } catch (Exception e) {
                ui.missingDate();
            }
            break;

        case TODO:
            try {
                myTask = new ToDo(str[1]);
            } catch (Exception e) {
                throw new DukeException("Oops! The description of a todo cannot be empty!");
            }
            break;

        case EVENT:
            String[] evnt;
            try {
                evnt = str[1].split(" /at ");
            } catch (Exception e) {
                throw new DukeException("Oops! The description of an event cannot be empty!");
            }

            try {
                myTask = new Event(evnt[0], evnt[1]);
            } catch (Exception e) {
                throw new DukeException("Oops! When is the event?");
            }
            break;
        }
        tasks.add(myTask);
        storage.writeFile(tasks);
        ui.add(tasks, myTask);
    }
}
