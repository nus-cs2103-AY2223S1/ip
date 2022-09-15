package neo;
import java.io.IOException;

/**
 * Un mark command class.
 */
public class UnMarkCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;

    Task task;

    /**
     * Un mark command constructor.
     *
     * @param ui User interface.
     * @param store Instance of storage class.
     * @param arrayLL Arraylist to store tasks.
     */
    public UnMarkCommand(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }

    /**
     * Un mark task as completed.
     *
     * @param taskNumber User input string.
     * @throws NeoException Exception neo.
     * @throws IOException Input output exception.
     */
    @Override
    String complete(String taskNumber) throws NeoException, IOException {
        int taskNumberInt = Integer.valueOf(taskNumber);
        assert arrayLL.getTask(taskNumberInt-1).getIsDone() == "X": "already unmarked";
        arrayLL.getTask(taskNumberInt-1).setIsDone(false);
        store.writeData(arrayLL.getTask(0));
        for (int i = 1; i < arrayLL.arrayL.size(); i++) {
            store.storeData(arrayLL.getTask(i));
        }
        return "ohk I've marked this task as not done" + "\n" +arrayLL.getTask(taskNumberInt-1).toString();
    }
}

