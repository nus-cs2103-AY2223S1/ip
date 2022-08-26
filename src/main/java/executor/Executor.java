package executor;

import java.time.LocalDate;
import java.util.ArrayList;

import belly.Belly;
import brain.Brain;
import duke.DukeException;
import processor.TaskDatetimeFormatter;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Executor {
    public final Brain brain;
    public final Belly belly;

    public Executor(Brain brain, Belly belly) {
        this.brain = brain;
        this.belly = belly;
    }
    public String showBrain(String commandDescription) throws DukeException {
        if (!commandDescription.equals("")) {
            throw DukeException.shouldHaveNoDescriptionError();
        }

        return brain.show();
    }

    public String markTask(String commandDescription) throws DukeException {
        if (commandDescription.equals("")) {
            throw DukeException.emptyCommandDescriptionError();
        }

        try {
            String result = "";
            int index = Integer.parseInt(commandDescription);
            Task task = brain.get(index - 1);
            task.markAsDone();

            result += "Yoohooo! Tob Tob has marked this task as done:\n";
            result += task.toString();
            belly.saveToHardDisk(brain.migrateBrainToTxt());
            return result;
        } catch (NumberFormatException e) {
            throw DukeException.integerIndexError("mark");
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.indexOutOfBoundsError(brain.size());
        }
    }

    public String unmarkTask(String commandDescription) throws DukeException {
        if (commandDescription.equals("")) {
            throw DukeException.emptyCommandDescriptionError();
        }

        try {
            String result = "";
            int index = Integer.parseInt(commandDescription);
            Task task = brain.get(index - 1);
            task.markAsUndone();

            result += "Saddd! Tob Tob has marked this task as not done yet:\n";
            result += task.toString();
            belly.saveToHardDisk(brain.migrateBrainToTxt());
            return result;
        } catch (NumberFormatException e) {
            throw DukeException.integerIndexError("unmark");
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.indexOutOfBoundsError(brain.size());
        }
    }

    public String putInBrain(String taskType, String[] taskDescriptionDatetime, int separatorIndex) throws DukeException {
        if (taskDescriptionDatetime[0].equals("")) {
            throw DukeException.emptyCommandDescriptionError();
        }

        Task task;
        String taskDescription = taskDescriptionDatetime[0];
        String datetimeString;
        LocalDate datetime;

        String result = "";
        String separator;

        switch (taskType) {
        case "todo":
            task = new Todo(taskDescription);
            brain.add(task);

            result += "Wiii! Now Tob Tob's brain has more stuffs\n";
            result += task.toString();
            result += "\n\n";
            result += brain.show();
            belly.saveToHardDisk(brain.migrateBrainToTxt());

            return result;
        case "deadline":
            separator = " /by ";
            if (separatorIndex == -1) {
                throw DukeException.taskIncorrectFormatError(separator, taskType);
            } else {
                datetimeString = taskDescriptionDatetime[1];
                datetime = TaskDatetimeFormatter.stringToDatetime(datetimeString);
                task = new Deadline(taskDescription, datetime);
                brain.add(task);

                result += "Wiii! Now Tob Tob's brain has more stuffs\n";
                result += task.toString();
                result += "\n\n";
                result += brain.show();
                belly.saveToHardDisk(brain.migrateBrainToTxt());

                return result;
            }
        default:
            separator = " /at ";
            if (separatorIndex == -1) {
                throw DukeException.taskIncorrectFormatError(separator, taskType);
            } else {
                datetimeString = taskDescriptionDatetime[1];
                datetime = TaskDatetimeFormatter.stringToDatetime(datetimeString);
                task = new Event(taskDescription, datetime);
                brain.add(task);

                result += "Wiii! Now Tob Tob's brain has more stuffs\n";
                result += task.toString();
                result += "\n\n";
                result += brain.show();
                belly.saveToHardDisk(brain.migrateBrainToTxt());

                return result;
            }
        }
    }

    public String trashFromBrain(String commandDescription) throws DukeException {
        if (commandDescription.equals("")) {
            throw DukeException.emptyCommandDescriptionError();
        }

        try {
            String result = "";
            int index = Integer.parseInt(commandDescription);
            Task task = brain.get(index - 1);
            brain.remove(index - 1);

            result += "Ewww! This task is no longer in Tob Tob's Brain:\n";
            result += task.toString();
            result += "\n";
            result += brain.show();
            belly.saveToHardDisk(brain.migrateBrainToTxt());

            return result;
        } catch (NumberFormatException e) {
            throw DukeException.integerIndexError("delete");
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.indexOutOfBoundsError(brain.size());
        }
    }

    public String hibernate() {
        String result = "";
        result += "Byeee! Tob Tob is sick of you\n";
        result += "Don't you dare come back\n";
        result += "Tob Tob hates you";

        return result;
    }

    /**
     * Returns a {@link String} of {@link Task}s that have the specified keyword.
     *
     * @param keyword a {@link String} that wants to be searched
     * @return String
     */
    public String findInBrain(String keyword) {
        String result = "";
        result += "Ulalaaa! Here are the matching tasks in your list:\n";

        result += brain.findInTask(keyword).toString();

        return result;
    }
}
