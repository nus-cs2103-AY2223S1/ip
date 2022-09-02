package neo;
import java.io.IOException;

public class MarkCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    /**
     * mark command constructor
     *
     * @param ui ui
     * @param stor instance of storage class
     * @param arrayLL arraylist to stor tasks
     */
    public MarkCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    /**
     * Mark command as completed
     *
     * @param tempi user input string
     * @throws NeoException
     * @throws IOException
     */
    @Override
    void complete(String tempi) throws NeoException, IOException {
        int tempii = Integer.valueOf(tempi);
        arrayLL.getTask(tempii-1).setIsDone(true);
        System.out.println("Nice! I've marked this task as done");
        System.out.println(arrayLL.getTask(tempii-1).toString());
        stor.Writedata(arrayLL.getTask(0));
        for(int i =1; i<arrayLL.arrayL.size(); i++) {
            stor.Storedata(arrayLL.getTask(i));
        }
    }
}
