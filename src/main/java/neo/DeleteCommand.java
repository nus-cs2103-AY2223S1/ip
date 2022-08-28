package neo;
import java.io.IOException;

public class DeleteCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    public DeleteCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    @Override
    void complete(String tempi) throws NeoException, IOException {
        int tempii = Integer.valueOf(tempi);
        System.out.println("ok, I've deleted this take from array");
        System.out.println(arrayLL.getTask(tempii-1).toString());
        arrayLL.delete(tempii-1);
    }
}
