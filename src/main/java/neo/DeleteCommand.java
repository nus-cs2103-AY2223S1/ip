package neo;
import java.io.IOException;

/**
 * Class to delete user command.
 */
public class DeleteCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;

    Task task;

    /**
     * Constructor of delete command.
     *
     * @param ui User interface.
     * @param store Instance of storage class.
     * @param arrayLL Arraylist to store tasks.
     */
    public DeleteCommand(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }

    /**
     * Deletes task from array.
     *
     * @param taskNumber User input string.
     * @throws NeoException Exception neo.
     * @throws IOException Input output exception.
     */
    @Override
    String complete(String taskNumber) throws NeoException, IOException {
        int taskNumberInt = Integer.valueOf(taskNumber);
        String t = arrayLL.getTask(taskNumberInt-1).toString();
        arrayLL.delete(taskNumberInt-1);
        store.writeData(arrayLL.getTask(0));
        for (int i = 1; i < arrayLL.arrayL.size(); i++) {
            store.storeData(arrayLL.getTask(i));
        }
        return t;
    }
}
