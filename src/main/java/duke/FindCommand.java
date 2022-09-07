package duke;

import java.util.ArrayList;

public class FindCommand extends Command{
    @Override
    String execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        int count = 0;
        String s = "";
        for (int i = 0; i < listOfTasks.size(); i++){
            if (listOfTasks.get(i).name.contains(fullCommand.substring(5))){
                count = count + 1;
                 s = s + "\n" + count + "." + listOfTasks.get(i).toString();
            } else {
                s = "No task with keyword found.";
            }
        }
        return s;
    }
}
