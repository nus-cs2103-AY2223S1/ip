package neo;
import java.io.IOException;

/**
 * Unmark command class.
 */
public class UnMarkCommand extends Command{

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    /**
     * Unmark command constructor.
     *
     * @param ui ui
     * @param stor instance of storage class
     * @param arrayLL arraylist to store tasks
     */
    public UnMarkCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    /**
     * Un mark task as completed.
     *
     * @param tempi user input string
     * @throws NeoException
     * @throws IOException
     */
    @Override
    void complete(String tempi) throws NeoException, IOException {
        int tempii = Integer.valueOf(tempi);
        arrayLL.getTask(tempii-1).setIsDone(false);
        System.out.println("ohk I've marked this task as not done");
        System.out.println(arrayLL.getTask(tempii-1).toString());
        stor.writeData(arrayLL.getTask(0));
        for(int i =1; i<arrayLL.arrayL.size(); i++) {
            stor.storeData(arrayLL.getTask(i));
        }
    }
}

