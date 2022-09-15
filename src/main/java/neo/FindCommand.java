package neo;

import java.io.IOException;

/**
 * Class to find task with specific keyword.
 */
public class FindCommand extends Command{

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    /**
     * Constructor of find command class.
     *
     * @param ui user interface
     * @param stor instance of storage class
     * @param arrayLL arrayList to store tasks
     */
    public FindCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    /**
     * Finds matching tasks.
     *
     * @param keyword user input string for keyword
     * @throws NeoException neo exception
     * @throws IOException Input output button
     */
    @Override
    String complete(String keyword) throws NeoException, IOException {
        System.out.println("Here are the matching tasks in your list:");
        String str = "";
        for(int i =0; i<arrayLL.arrayL.size(); i++) {
            String taskDescription = String.valueOf(arrayLL.getTask(i));
            if (taskDescription.contains(keyword)) {
                str += i+1 + ". " + taskDescription + "\n";
            }
        }
        return str;
    }
}
