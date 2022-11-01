package neo;
import java.io.IOException;

public class PriorityCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;
    private int type;

    Task task;

    /**
     * mark command constructor
     *
     * @param ui User interface.
     * @param store Instance of storage class.
     * @param arrayLL Arraylist to store tasks
     */
    public PriorityCommand(Ui ui, Storage store, TaskList arrayLL, int type) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
        this.type = type;
    }

    /**
     * Marks command as completed.
     *
     * @param userText User input string.
     * @throws NeoException Exception neo.
     * @throws IOException Input output exception.
     */
    @Override
    String complete(String userText) throws NeoException, IOException {
        String arr[];
        arr = userText.split(" ", 2);
        String command = arr[0];
        String tempi = arr[1];
        int tempii = Integer.valueOf(tempi);
        arrayLL.getTask(tempii-1).setPriority(command);
        store.writeData(arrayLL.getTask(0));
        for (int i = 1; i < arrayLL.arrayL.size(); i++) {
            store.storeData(arrayLL.getTask(i));
        }
        return "Nice! I've added priority to this task" + "\n" + arrayLL.getTask(tempii-1).toString();
    }
}
