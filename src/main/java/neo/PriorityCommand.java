package neo;
import java.io.IOException;

public class PriorityCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;
    private int type;

    Task task;

    /**
     * mark command constructor
     *
     * @param ui ui
     * @param stor instance of storage class
     * @param arrayLL arraylist to store tasks
     */
    public PriorityCommand(Ui ui, Storage stor, TaskList arrayLL, int type) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
        this.type = type;
    }

    /**
     * Mark command as completed
     *
     * @param userText user input string
     * @throws NeoException
     * @throws IOException
     */
    @Override
    String complete(String userText) throws NeoException, IOException {
        String arr[];
        arr = userText.split(" ", 2);
        String command = arr[0];
        String tempi = arr[1];
        int tempii = Integer.valueOf(tempi);
        arrayLL.getTask(tempii-1).setPriority(command);
        stor.writeData(arrayLL.getTask(0));
        for (int i = 1; i < arrayLL.arrayL.size(); i++) {
            stor.storeData(arrayLL.getTask(i));
        }
        return "Nice! I've added priority to this task" + "\n" + arrayLL.getTask(tempii-1).toString();
    }
}
