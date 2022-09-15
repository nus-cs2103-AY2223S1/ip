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
        assert saveFile != null : "No save file provided.";

        this.saveFile = saveFile;
        saveFile.initialise(tempStorage);
    }

    /**
     * Adds Tasks to storage and saves them to the hard disk.
     *
     * @param command the user's input.
     * @return A response to be displayed to the user.
     */
    public String addTask(String command) {
        assert command != null : "No command provided.";
        assert tempStorage != null : "No temporary storage created.";
        assert saveFile != null : "No save file provided.";

        Task newTask;

        if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                return Ui.emptyDescription("Todo");
            }

            newTask = new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            try {
                if (command.length() <= 9) {
                    return Ui.emptyDescription("Deadline");
                }

                String[] temp = command.substring(9).split("/by ");

                String checkForValidCommand = checkForEmptyField(temp);
                if (checkForValidCommand != null) {
                    return checkForValidCommand;
                }

                newTask = new Deadline(temp[0], temp[1]);

            } catch (DateTimeParseException e) {
                return Ui.wrongDateFormat();

            }
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                return Ui.emptyDescription("Event");
            }

            String[] temp = command.substring(6).split("/at ");

            String checkForValidCommand = checkForEmptyField(temp);
            if (checkForValidCommand != null) {
                return checkForValidCommand;
            }

            newTask = new Event(temp[0], temp[1]);

        } else if (command.startsWith("FixedDuration")) {
            if (command.length() <= 14) {
                return Ui.emptyDescription("FixedDuration");
            }

            String[] temp = command.substring(14).split("/needs ");

            String checkForValidCommand = checkForEmptyField(temp);
            if (checkForValidCommand != null) {
                return checkForValidCommand;
            }

            newTask = new FixedDurationTask(temp[0], temp[1]);

        } else {
            return Ui.unknownCommand();

        }

        tempStorage.add(newTask);
        assert tempStorage.contains(newTask) : "Task failed to be added to temporary storage";

        saveFile.addTask(newTask.toStore());

        return Ui.addText(tempStorage.get(tempStorage.size() - 1).toString(), tempStorage.size());
    }

    private String checkForEmptyField(String[] temp) {
        if (temp.length < 2 || temp[1].length() == 0) {
            return Ui.emptyField();
        }

        if (temp[0].length() == 0) {
            return Ui.emptyDescription("Deadline");
        }

        return null;
    }

    /**
     *  Removes the specified Task from storage if it exists.
     *
     *  If the specified Task does not exist, a statement telling the user that
     *  the specified does not exist is printed.
     *
     * @param index The index of the Task to be removed from storage.
     * @return A response to be displayed to the user.
     */
    public String delete(int index) {
        assert index >= 0 : "Index does not exist.";
        assert tempStorage != null : "No temporary storage created.";
        assert saveFile != null : "No save file provided.";

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
     *
     * @return A response to be displayed to the user.
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
     * @return A response to be displayed to the user.
     */
    public String markOrUnmarkAsDone(String command) {
        assert command != null : "No command provided.";

        int index = Character.getNumericValue(command.charAt(command.length() - 1));

        try {
            String temp = tempStorage.get(index - 1).markOrUnmarkAsDone(command);
            saveFile.reload(tempStorage);
            return temp;
        } catch (IndexOutOfBoundsException e) {
            return Ui.taskNotFoundText();
        }
    }

    /**
     * Returns a list of Tasks that contain the word to be found.
     *
     * @param command the user's input.
     * @return A response to be displayed to the user.
     */
    public String find(String command) {
        assert command != null : "No command provided.";

        ArrayList<Task> result = new ArrayList<>();
        String toFind = command.substring(5);

        Iterator<Task> iterator = tempStorage.iterator();
        while (iterator.hasNext()) {
            Task curr = iterator.next();
            if (curr.checkMatching(toFind)) {
                result.add(curr);
            }
        }

        String found = "Here are the matching tasks in your list: \n";

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
