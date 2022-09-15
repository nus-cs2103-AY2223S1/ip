package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Types of statistics available
 **/
enum StatisticType {
    COUNT,
}

/**
 * Valid targets to derive statistics from
 **/
enum StatisticTarget {
    TODOS,
    DEADLINES,
    EVENTS,
    ALL,
}

/**
 * Statistics command to be executed.
 */
public class StatisticsCommand extends Command {
    private final String[] args;
    private final StatisticType statistic_type;
    private final StatisticTarget statistic_target;

    /**
     * Constructor for Statistics command.
     *
     * @param args Arguments to instantiate the command.
     */
    public StatisticsCommand(String[] args) {
        assert isValidStatisticType(args[0]) : "Invalid statistic type. Is there missing validation?";
        assert isValidStatisticTarget(args[1]) : "Invalid statistic target. Is there missing validation?";
        this.args = args;
        this.statistic_type = StatisticType.valueOf(args[0].toUpperCase());
        this.statistic_target = StatisticTarget.valueOf(args[1].toUpperCase());
    }

    /**
     * Validates given arguments for StatisticsCommand.
     *
     * @param args Arguments to validate.
     * @throws DukeException Thrown if any arguments are invalid.
     */
    public static void validateArguments(String[] args) throws DukeException {
        if (args.length == 0) {
            throw new DukeException("Missing statistic type! Please specify what kind of statistic you want to find.");
        }
        if (args.length == 1) {
            throw new DukeException("Missing statistic target! Please specify the target of your statistic."
                    + "This can be todos, deadlines, events, or all.");
        }

        String statisticType = args[0];
        String statisticTarget = args[1];

        if (!isValidStatisticType(statisticType)) {
            throw new DukeException("Invalid statistic type provided! Available types are: count");
        }
        if (!isValidStatisticTarget(statisticTarget)) {
            throw new DukeException("Invalid statistic target provided! "
                    + "Available targets are: todos, deadlines, events, all");
        }
    }

    /**
     * Returns true if the string is a valid statistic type.
     *
     * @param str String to validate
     * @return Boolean value representing if string is a valid statistic type
     */
    public static boolean isValidStatisticType(String str) {
        for (StatisticType cmd : StatisticType.values()) {
            if (str.equalsIgnoreCase(cmd.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the string is a valid statistic target.
     *
     * @param str String to validate.
     * @return Boolean value representing if string is a valid statistic target.
     */
    public static boolean isValidStatisticTarget(String str) {
        for (StatisticTarget cmd : StatisticTarget.values()) {
            if (str.equalsIgnoreCase(cmd.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtain the count of a specific target, e.g. ToDos.
     *
     * @param target Task to obtain the count of.
     * @return Count of the specified task in the task list.
     */
    public String getStatisticCount(StatisticTarget target, TaskList taskList) {
        String msg = "";
        switch (target) {
        case TODOS:
            msg = getTodosCount(taskList);
            break;
        case DEADLINES:
            msg = getDeadlinesCount(taskList);
            break;
        case EVENTS:
            msg = getEventsCount(taskList);
            break;
        case ALL:
            msg = getTaskListSize(taskList);
            break;
        default:
            assert false : "Unhandled Task type when obtaining count in task list.";
            break;
        }
        return msg;
    }


    @Override
    public String execute(TaskList taskList) {
        String msg = "";
        switch (statistic_type) {
        case COUNT: {
            msg = getStatisticCount(statistic_target, taskList);
            break;
        }
        default: {
            assert false : "Unhandled statistic type in switch statement within execute function.";
            break;
        }
        }

        assert !msg.equals("") : "Missing message to return to ChatResponse within StatisticsCommand";
        return msg;
    }

    /**
     * Obtains frequency of ToDos in taskList, returns message with count.
     *
     * @param taskList Task list to count the ToDos.
     * @return Formatted string with count of ToDos.
     */
    public String getTodosCount(TaskList taskList) {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof ToDo) {
                count++;
            }
        }

        return ChatResponse.returnStatisticTaskCount("ToDos", count);
    }

    /**
     * Obtains frequency of Deadlines in taskList, returns message with count.
     *
     * @param taskList Task list to count the Deadlines.
     * @return Formatted string with count of Deadlines.
     */
    public String getDeadlinesCount(TaskList taskList) {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Deadline) {
                count++;
            }
        }

        return ChatResponse.returnStatisticTaskCount("Deadlines", count);
    }

    /**
     * Obtains frequency of Events in taskList, returns message with count.
     *
     * @param taskList Task list to count the Events.
     * @return Formatted string with count of Events.
     */
    public String getEventsCount(TaskList taskList) {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Event) {
                count++;
            }
        }

        return ChatResponse.returnStatisticTaskCount("Events", count);
    }

    /**
     * Obtains size of taskList, returns message with count.
     *
     * @param taskList Task list to obtain size of.
     * @return Formatted string with size of task list.
     */
    public String getTaskListSize(TaskList taskList) {
        return ChatResponse.returnStatisticTaskCount("the tasklist", taskList.size());
    }

}
