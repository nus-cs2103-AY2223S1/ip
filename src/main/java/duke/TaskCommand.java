package duke;

import java.util.ArrayList;

public abstract class TaskCommand extends Command {
    @Override
    String execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        try {
            handleEmptyTask(fullCommand);
            if (gotDuplicateTask(fullCommand, listOfTasks) != true) {
                return addTaskToList(fullCommand, listOfTasks);
            } else {
                return "Task has already been added.";
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }

    abstract void handleEmptyTask(String fullCommand) throws DukeException;
    abstract String addTaskToList(String fullCommand, ArrayList<Task> listOfTasks);
    abstract boolean isTaskEmpty(String fullCommand);

    boolean gotDuplicateTask(String fullCommand, ArrayList<Task> listOfTasks) {
        String s = "";
        String typeOfTask = fullCommand.substring(0,1);
        boolean gotDuplicate = false;
        if(typeOfTask.equals("t")){
            s = fullCommand.substring(5);
        } else if (typeOfTask.equals("d")) {
            s = fullCommand.substring(9, fullCommand.indexOf("/") - 1);
        } else {
            s =  fullCommand.substring(6, fullCommand.indexOf("/") - 1);
        }
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (s.equals(listOfTasks.get(i).name)) {
                gotDuplicate = true;
            }
        }
        return gotDuplicate;
    }
}
