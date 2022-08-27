import java.util.ArrayList;

public class GetCommand extends Command {
    private String inputString;
    public GetCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("get");
        }
        String inputDate = inputString.split(" ")[1];
        if (!HandleTime.isValidDate(inputDate)) {
            throw new KirbyMissingArgumentException("get");
        }
        ArrayList<Task> res = HandleTime.getDate(tasks.getList(), inputDate);
        for (Task re : res) {
            System.out.println(re.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
