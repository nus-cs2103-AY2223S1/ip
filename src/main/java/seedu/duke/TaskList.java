package seedu.duke;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private static ArrayList<Task> storage = new ArrayList<>();
    private Storage saveFile;

    /**
     * A constructor that returns an instance of TaskList.
     *
     * @param saveFile Handles storing Tasks in the hard disk.
     */
    public TaskList(Storage saveFile) {
        this.saveFile = saveFile;
        saveFile.initialise(storage);
    }

    /**
     * Adds Tasks to storage and saves them to the hard disk.
     *
     * @param command the user's input.
     */
    public void addTask(String command) {

        if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                Ui.emptyDescription("Todo");
                return;
            }

            Task newTask = new Todo(command.substring(5));
            storage.add(newTask);
        } else if (command.startsWith("deadline")) {
            try {
                if (command.length() <= 9) {
                    Ui.emptyDescription("Deadline");
                    return;
                }

                String[] temp = command.substring(9).split("/by ");

                Task newTask = new Deadline(temp[0], temp[1]);
                storage.add(newTask);
                saveFile.addTask(newTask.toStore());
            } catch (DateTimeParseException e) {
                Ui.wrongDateFormat();
                return;
            }
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                Ui.emptyDescription("Event");
                return;
            }

            String[] temp = command.substring(6).split("/at ");

            Task newTask = new Event(temp[0], temp[1]);
            storage.add(newTask);
            saveFile.addTask(newTask.toStore());
        } else {
            Ui.unknownCommand();
            return;
        }

        Ui.addText(storage.get(storage.size() - 1).toString(), storage.size());
    }

    /**
     *  Removes the specified Task from storage if it exists.
     *
     *  If the specified Task does not exist, a statement telling the user that
     *  the specified does not exist is printed.
     *
     * @param index The index of the Task to be removed from storage.
     */
    public void delete(int index) {
        try {
            Task toRemove = storage.remove(index);
            saveFile.reload(storage);

            Ui.deleteText(toRemove.toString(), storage.size());
        } catch (IndexOutOfBoundsException e) {
            Ui.taskNotFoundText();
        }

    }

    /**
     * Lists all the tasks currently being stored.
     */
    public void list() {
        System.out.println("____________________________________________________________ \n"
                + "Here are the tasks in your list:");

        int index = 1;
        Iterator<Task> iterator = storage.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%d. ", index);
            System.out.println(iterator.next().toString());
            index++;
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the specified Task as done or not done, according to the command.
     *
     * @param command The user's input.
     */
    public void toggleDone(String command) {
        int index = Character.getNumericValue(command.charAt(command.length() - 1));

        try {
            storage.get(index - 1).toggleDone(command);
            saveFile.reload(storage);
        } catch (IndexOutOfBoundsException e) {
            Ui.taskNotFoundText();
        }
    }


}
