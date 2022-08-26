package duke;

import java.util.ArrayList;

public class FindCommand extends Command{
    @Override
    void execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        int count = 0;
        for (int i = 0; i < listOfTasks.size(); i++){
            if (listOfTasks.get(i).name.contains(fullCommand.substring(5))){
                count = count + 1;
                System.out.println(count + "." + listOfTasks.get(i).toString());
            } else {

            }
        }
    }
}
