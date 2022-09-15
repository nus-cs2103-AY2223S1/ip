package neo;
import java.io.IOException;

/**
 * Class to delete user command.
 */
public class DeleteCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    /**
     * Constructor of delete command.
     *
     * @param ui ui
     * @param stor instance of storage class
     * @param arrayLL arraylist to stor tasks
     */
    public DeleteCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    /**
     * Delete task from array.
     *
     * @param tempi user input string
     * @throws NeoException
     * @throws IOException
     */
    @Override
    String complete(String tempi) throws NeoException, IOException {
        int tempii = Integer.valueOf(tempi);
        String t = arrayLL.getTask(tempii-1).toString();
        arrayLL.delete(tempii-1);
        stor.writeData(arrayLL.getTask(0));
        for (int i = 1; i < arrayLL.arrayL.size(); i++) {
            stor.storeData(arrayLL.getTask(i));
        }
        return t;
    }
}
