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
     * @param ui ui
     * @param stor instance of storage class
     * @param arrayLL arrayList to store tasks
     */
    public FindCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    /**
     * Function to find matching tasks.
     *
     * @param tempi user input string for keyword
     * @throws NeoException neo excpetion
     * @throws IOException Input output button
     */
    @Override
    String complete(String tempi) throws NeoException, IOException {
        System.out.println("Here are the matching tasks in your list:");
        String str = "";
        for(int i =0; i<arrayLL.arrayL.size(); i++) {
            String tempii = String.valueOf(arrayLL.getTask(i));
            if (tempii.contains(tempi)) {
                //System.out.println(tempii);
                str += i + tempii + "\n";
            }
        }
        return str;
    }
}
