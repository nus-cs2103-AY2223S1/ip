package seedu.duke;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private static ArrayList<Task> tempStorage = new ArrayList<>();
    private Storage saveFile;

    /**
     * A constructor that returns an instance of TaskList.
     *
     * @param saveFile Handles storing Tasks in the hard disk.
     */
    public TaskList(Storage saveFile) {
        this.saveFile = saveFile;
        saveFile.initialise(tempStorage);
    }

    /**
     * Adds Tasks to storage and saves them to the hard disk.
     *
     * @param command the user's input.
     */
    public String addTask(String command) {

        if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                return Ui.emptyDescription("Todo");
            }

            Task newTask = new Todo(command.substring(5));
            tempStorage.add(newTask);
            saveFile.addTask(newTask.toStore());
        } else if (command.startsWith("deadline")) {
            try {
                if (command.length() <= 9) {
                    return Ui.emptyDescription("Deadline");
                }

                String[] temp = command.substring(9).split("/by ");

                Task newTask = new Deadline(temp[0], temp[1]);
                tempStorage.add(newTask);
                saveFile.addTask(newTask.toStore());
            } catch (DateTimeParseException e) {
                return Ui.wrongDateFormat();
            }
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                return Ui.emptyDescription("Event");
            }

            String[] temp = command.substring(6).split("/at ");

            Task newTask = new Event(temp[0], temp[1]);
            tempStorage.add(newTask);
            saveFile.addTask(newTask.toStore());
        } else {
            return Ui.unknownCommand();
        }

        return Ui.addText(tempStorage.get(tempStorage.size() - 1).toString(), tempStorage.size());
    }

    /**
     *  Removes the specified Task from storage if it exists.
     *
     *  If the specified Task does not exist, a statement telling the user that
     *  the specified does not exist is printed.
     *
     * @param index The index of the Task to be removed from storage.
     */
    public String delete(int index) {
        try {
            Task toRemove = tempStorage.remove(index);
            saveFile.reload(tempStorage);

            return Ui.deleteText(toRemove.toString(), tempStorage.size());
        } catch (IndexOutOfBoundsException e) {
            return Ui.taskNotFoundText();
        }

    }

    /**
     * Lists all the tasks currently being stored.
     */
    public String list() {
        String list = "Here are the tasks in your list:\n";

        int index = 1;
        Iterator<Task> iterator = tempStorage.iterator();
        while (iterator.hasNext()) {
            String task = index + ". " + iterator.next().toString() + "\n";
            list = list + task;

            index++;
        }

        return list;
    }

    /**
     * Marks the specified Task as done or not done, according to the command.
     *
     * @param command The user's input.
     */
    public String toggleDone(String command) {
        int index = Character.getNumericValue(command.charAt(command.length() - 1));

        try {
            String temp = tempStorage.get(index - 1).toggleDone(command);
            saveFile.reload(tempStorage);
            return temp;
        } catch (IndexOutOfBoundsException e) {
            return Ui.taskNotFoundText();
        }
    }

    /**
     * returns a list of Tasks that contain the word to be found.
     * @param command the user's input.
     */
    public String find(String command) {
        ArrayList<Task> result = new ArrayList<>();
        String toFind = command.substring(5);

        Iterator<Task> iterator = tempStorage.iterator();
        while (iterator.hasNext()) {
            Task curr = iterator.next();
            if (curr.checkMatching(toFind)) {
                result.add(curr);
            }
        }

        String found = "Here are the matching tasks in your list:";

        int index = 1;
        Iterator<Task> resultIterator = result.iterator();
        while (resultIterator.hasNext()) {
            String task = index + ". " + resultIterator.next().toString() + "\n";
            found = found + task;
            index++;
        }

        return found;
    }


}
