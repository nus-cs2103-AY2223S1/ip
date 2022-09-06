package duke;

import java.util.ArrayList;

public class FindCommand extends Command {
    int numbering = 0;
    String listOfTasksWithKeyword = "";

    @Override
    String execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        return returnListOfTasksWithKeyword(fullCommand, listOfTasks);
    }

    String returnListOfTasksWithKeyword(String fullCommand, ArrayList<Task>list) {
        for (int i = 0; i < list.size(); i++) {
            appendStringIfHaveKeyWord(fullCommand, list, i);
        }
        return listOfTasksWithKeyword;
    }

    boolean checkIfTaskHasKeyWord(String fullCommand, ArrayList<Task> list, int i) {
       return list.get(i).name.contains(fullCommand.substring(5));
    }

    String appendEachTaskToString(String s, ArrayList<Task> list, int i) {
        return s + "\n" + numbering + "." + list.get(i).toString();
    }

    void appendStringIfHaveKeyWord(String fullCommand,ArrayList<Task>list, int i) {
        if (checkIfTaskHasKeyWord(fullCommand, list, i)) {
            numbering = numbering + 1;
            listOfTasksWithKeyword = appendEachTaskToString(listOfTasksWithKeyword,list,i);
        }
    }
}
