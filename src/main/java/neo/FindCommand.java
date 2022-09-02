package neo;

import java.io.IOException;

public class FindCommand extends Command{

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    public FindCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    @Override
    void complete(String tempi) throws NeoException, IOException {
        System.out.println("Here are the matching tasks in your list:");
        for(int i =0; i<arrayLL.arrayL.size(); i++) {
            String tempii = String.valueOf(arrayLL.getTask(i));
            if (tempii.contains(tempi)) {
                System.out.println(tempii);
            }
        }
    }
}
