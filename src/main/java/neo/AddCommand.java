package neo;
import java.io.IOException;

/**
 * Class to Add user Command.
 */
public class AddCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;
    private int type;

    /**
     * AddCommand constructor.
     *
     * @param ui User interface.
     * @param store Instance of storage class.
     * @param arrayLL Arraylist to store tasks.
     * @param type Integer to identify type of task.
     */
    public AddCommand(Ui ui, Storage store, TaskList arrayLL, int type) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
        this.type = type;
    }

    /**
     * Adds task to array depending on type of task.
     *
     * @param taskNumber user input string
     * @throws NeoException
     * @throws IOException
     */
    @Override
    String complete(String taskNumber) throws NeoException, IOException {

        if (type ==0) {
            String arri[];
            arri = taskNumber.split("/by ");
            String temp2 = arri[0];
            String temp3 = arri[1];

            Deadline d = new Deadline(temp2, temp3);
            arrayLL.addTask(d);
            store.storeData(d);
            return "Added this task to array: " + d.toString();
        }
        if (type ==1) {
            String arri[];
            arri = taskNumber.split("/at ");
            String temp2 = arri[0];
            String temp3 = arri[1];

            Event e = new Event(temp2, temp3);
            arrayLL.addTask(e);
            store.storeData(e);
            return "Added this task to array: " + e.toString();
        }
        if (type ==2) {
            ToDo td = new ToDo(taskNumber);
            arrayLL.addTask(td);
            store.storeData(td);
            return "Added this task to array: " + td.toString();
        }
        return "";
    }
}
