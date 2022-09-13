package executor;

import java.time.LocalDate;

import belly.Belly;
import brain.Brain;
import processor.TaskDatetimeFormatter;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tobtob.TobTobException;

/**
 * Represents an executor that execute whatever thing a parser tells from the command
 */
public class Executor {
    public final Brain brain;
    public final Belly belly;

    /**
     * Executor constructor with the specified brain and belly
     *
     * @param brain a {@link Brain} instance
     * @param belly a {@link Belly} instance
     */
    public Executor(Brain brain, Belly belly) {
        this.brain = brain;
        this.belly = belly;
    }

    /**
     * Returns a {@link String} representation of {@link Task}s inside <code>brain</code>.
     *
     * @param commandDescription a {@link String} passed by parser
     * @return {@link String}
     * @throws TobTobException If {@code commandDescription} is an empty string
     */
    public String showBrain(String commandDescription) throws TobTobException {
        if (!commandDescription.equals("")) {
            throw TobTobException.shouldHaveNoDescriptionError("list");
        }

        assert commandDescription.equals("") : "command description shouldn't be empty at this point";

        return brain.show();
    }

    /**
     * Returns a {@link String} indicating the marked {@link Task}.
     *
     * @param commandDescription a {@link String} passed by parser
     * @return {@link String}
     * @throws TobTobException If {@code commandDescription} is an empty {@link String}/not an {@link Integer}/bigger
     *      than total {@link Task}s
     */
    public String markTask(String commandDescription) throws TobTobException {
        if (commandDescription.equals("")) {
            throw TobTobException.emptyCommandDescriptionError();
        }

        assert !commandDescription.equals("") : "command description shouldn't be empty at this point";

        try {
            String result = "";
            int index = Integer.parseInt(commandDescription);
            Task task = brain.getTask(index - 1);

            result += "Yoohooo! Tob Tob has marked this task as done:\n";
            result += task.markAsDone();
            belly.saveToHardDisk(brain.migrateBrainToTxt());
            return result;
        } catch (NumberFormatException e) {
            throw TobTobException.integerIndexError("mark");
        } catch (IndexOutOfBoundsException e) {
            throw TobTobException.indexOutOfBoundsError(brain.getSize());
        }
    }

    /**
     * Returns a {@link String} indicating the unmarked {@link String}.
     *
     * @param commandDescription a {@link String} passed by parser
     * @return {@link String}
     * @throws TobTobException If {@code commandDescription} is an empty {@link String}/not an {@link Integer}/bigger
     *      than total {@link Task}s
     */
    public String unmarkTask(String commandDescription) throws TobTobException {
        if (commandDescription.equals("")) {
            throw TobTobException.emptyCommandDescriptionError();
        }

        assert !commandDescription.equals("") : "command description shouldn't be empty at this point";

        try {
            String result = "";
            int index = Integer.parseInt(commandDescription);
            Task task = brain.getTask(index - 1);

            result += "Saddd! Tob Tob has marked this task as not done yet:\n";
            result += task.markAsUndone();
            belly.saveToHardDisk(brain.migrateBrainToTxt());
            return result;
        } catch (NumberFormatException e) {
            throw TobTobException.integerIndexError("unmark");
        } catch (IndexOutOfBoundsException e) {
            throw TobTobException.indexOutOfBoundsError(brain.getSize());
        }
    }

    /**
     * Returns a <code>String</code> indicating the new <code>Task</code>.
     *
     * @param taskType type of {@link Task}
     * @param taskDescriptionDatetime {@code String[]} of taskDescription and datetime
     * @param separatorIndex int index of the separator
     * @return <code>String</code>
     * @throws TobTobException If {@code commandDescription} is an empty {@link String}/{@code commandDescription}
     *      for {@link Deadline} is not separated using " /by "/{@code commandDescription}
     *      for {@link Deadline} is not separated using " /at "/{@code taskType} is unrecognized
     */
    public String putInBrain(String taskType, String[] taskDescriptionDatetime, int separatorIndex)
            throws TobTobException {
        if (taskDescriptionDatetime[0].equals("")) {
            throw TobTobException.emptyCommandDescriptionError();
        }

        assert !taskDescriptionDatetime[0].equals("") : "command description shouldn't be empty at this point";

        Task task;
        String taskDescription = taskDescriptionDatetime[0];
        String datetimeString;
        LocalDate datetime;

        String result = "";
        String separator;

        switch (taskType) {
        case "todo":
            task = new Todo(taskDescription);

            result += "Wiii! Now Tob Tob's brain has more stuffs\n";
            result += brain.addTask(task);
            result += "\n\n";
            result += brain.show();
            belly.saveToHardDisk(brain.migrateBrainToTxt());

            return result;
        case "deadline":
            separator = " /by ";
            if (separatorIndex == -1) {
                throw TobTobException.taskIncorrectFormatError(separator, taskType);
            } else {
                datetimeString = taskDescriptionDatetime[1];
                datetime = TaskDatetimeFormatter.convertStringToDatetime(datetimeString);
                task = new Deadline(taskDescription, datetime);

                result += "Wiii! Now Tob Tob's brain has more stuffs\n";
                result += brain.addTask(task);
                result += "\n\n";
                result += brain.show();
                belly.saveToHardDisk(brain.migrateBrainToTxt());

                return result;
            }
        case "event":
            separator = " /at ";
            if (separatorIndex == -1) {
                throw TobTobException.taskIncorrectFormatError(separator, taskType);
            } else {
                datetimeString = taskDescriptionDatetime[1];
                datetime = TaskDatetimeFormatter.convertStringToDatetime(datetimeString);
                task = new Event(taskDescription, datetime);

                result += "Wiii! Now Tob Tob's brain has more stuffs\n";
                result += brain.addTask(task);
                result += "\n\n";
                result += brain.show();
                belly.saveToHardDisk(brain.migrateBrainToTxt());

                return result;
            }
        default:
            throw TobTobException.unrecognizedTaskType();
        }
    }

    /**
     * Returns a {@link String} indicating the trashed {@link Task}.
     *
     * @param commandDescription a {@link String} passed by parser
     * @return {@link String}
     * @throws TobTobException If {@code commandDescription} is an empty {@link String}/not an {@link Integer}/bigger
     *      than total {@link Task}s
     */
    public String trashFromBrain(String commandDescription) throws TobTobException {
        if (commandDescription.equals("")) {
            throw TobTobException.emptyCommandDescriptionError();
        }

        assert !commandDescription.equals("") : "command description shouldn't be empty at this point";

        try {
            String result = "";
            int index = Integer.parseInt(commandDescription);

            result += "Ewww! This task is no longer in Tob Tob's Brain:\n";
            result += brain.removeTask(index - 1);
            result += "\n";
            result += brain.show();
            belly.saveToHardDisk(brain.migrateBrainToTxt());

            return result;
        } catch (NumberFormatException e) {
            throw TobTobException.integerIndexError("delete");
        } catch (IndexOutOfBoundsException e) {
            throw TobTobException.indexOutOfBoundsError(brain.getSize());
        }
    }

    /**
     * Returns a {@link String} of {@link Task}s that have the specified keyword.
     *
     * @param keyword a {@link String} that wants to be searched
     * @return {@link String}
     */
    public String findInBrain(String keyword) {
        String result = "";
        result += "Ulalaaa! Here are the matching tasks in your list:\n";

        result += brain.findInTask(keyword).toString();

        return result;
    }

    /**
     * Returns a {@link String} indicating the hibernating.
     *
     * @param commandDescription a {@link String} passed by parser
     * @return {@link String}
     * @throws TobTobException If {@code commandDescription} is not empty.
     */
    public String hibernate(String commandDescription) throws TobTobException {
        if (!commandDescription.equals("")) {
            throw TobTobException.shouldHaveNoDescriptionError("bye");
        }

        assert commandDescription.equals("") : "there shouldn't be any command description at this point";

        String result = "";
        result += "Byeee! Tob Tob is sick of you\n";
        result += "Don't you dare come back\n";
        result += "Tob Tob hates you";

        return result;
    }

    /**
     * Returns a {@link String} of the short guide.
     *
     * @param commandDescription a {@link String} passed by parser
     * @return {@link String}
     * @throws TobTobException If {@code commandDescription} is not empty.
     */
    public String showShortGuide(String commandDescription) throws TobTobException {
        if (!commandDescription.equals("")) {
            throw TobTobException.shouldHaveNoDescriptionError("help");
        }

        String result = "";
        result += "Hi there! Here are the available commands that Tob Tob understands (Don't forget to replace "
                + "the CAPITALIZED_WORDS)\n";
        result += "- To add a todo task, type \n  \"todo TODO_NAME\"\n";
        result += "- To add a deadline task, type \n  \"deadline DEADLINE_NAME \\by YYYY-MM-DD\"\n";
        result += "- To add an event task, type \n  \"event EVENT_NAME \\at YYYY-MM-DD\"\n";
        result += "- To list all the tasks, type \n  \"list\"\n";
        result += "- To mark a task, type \n  \"mark TASK_NUMBER\"\n";
        result += "- To unmark a task, type \n  \"unmark TASK_NUMBER\"\n";
        result += "- To delete a task, type \n  \"delete TASK_NUMBER\"\n";
        result += "- To find a task, type \n  \"find TASK_NAME\"\n";
        result += "- To see the list of available commands, type \n  \"help\"\n";
        result += "- To say goodbye to Tob Tob, type \n  \"bye\"";

        return result;
    }
}
