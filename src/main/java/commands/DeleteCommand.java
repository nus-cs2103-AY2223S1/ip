package commands;

import java.util.ArrayList;

import duke.Statements;
import tasks.*;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        String descript = taskList.get(index).toString();
        taskList.remove(index);
        System.out.println("Okay! The task: \n" + descript + "\nhas been deleted forever.\n" +
                "You have " + taskList.size() + " task" + ((taskList.size()!=1)?"s ":" ") + "left!");
    }

}
