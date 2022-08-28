package neo;
import java.io.IOException;

public class UnMarkCommand extends Command{

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    public UnMarkCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    @Override
    void complete(String tempi) throws NeoException, IOException {
        int tempii = Integer.valueOf(tempi);
        arrayLL.getTask(tempii-1).setIsDone(false);
        System.out.println("ohk I've marked this task as not done");
        System.out.println(arrayLL.getTask(tempii-1).toString());
    }
}

