package dobby;

import java.util.Arrays;
/**
 * UserInput is a class that deals with interactions with the user.
 */
public class UserInput {
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_SIMPLIFY = "simplify";
    private static final String NO_DATE_ERROR = "noDate";
    private static final String WRONG_DEADLINE_FORMAT = "wrongDeadline";
    private static final String WRONG_EVENT_FORMAT = "wrongEvent";
    private static final String DEADLINE_COMMAND = "by";
    private static final String EVENT_COMMAND = "at";
    private static String mark = "m";
    private static String list = "l";
    private static String unmark = "um";
    private static String delete = "del";
    private static String todo = "t";
    private static String find = "f";
    private static String deadline = "d";
    private static String event = "e";
    private static String simplify = "s";
    private static String[] commandList =
        {COMMAND_MARK, COMMAND_UNMARK, COMMAND_DELETE, COMMAND_TODO, COMMAND_FIND, DEADLINE_COMMAND, COMMAND_EVENT,
            COMMAND_SIMPLIFY, COMMAND_LIST, list, mark, unmark, delete, todo, find, deadline, event, simplify};
    private static String newCmd;
    private static String initialCmd;
    private String taskType;
    private String rest;
    private int ind;
    private String desc;
    private String date;

    /**
     * Reads the user input
     * and updates all required information
     * and returns a string of the task type.
     *
     * @return String of the task type.
     */
    public String readCommand(String input) {
        try {
            if (!input.contains(" ")) {
                if (checkTaskType(input, COMMAND_SIMPLIFY, simplify)) {
                    setTaskType(COMMAND_SIMPLIFY);
                    initialCmd = "missingInitialCommand";
                    newCmd = "missingNewCommand";
                } else if (checkTaskType(input, COMMAND_LIST, list)) {
                    setTaskType(COMMAND_LIST);
                } else {
                    taskType = input;
                }
            } else {
                taskType = Parser.getTaskType(input);
                rest = Parser.getRestOfCommand(input);
                setCommand();
            }
        } catch (java.lang.NumberFormatException e) {
            DobbyChat.noNumber();
        }
        return taskType;
    }

    /**
     * Sets the commands based on user input.
     */
    private void setCommand() {
        if (checkTaskType(COMMAND_MARK, mark)) {
            setMarkCommand();
        } else if (checkTaskType(COMMAND_UNMARK, unmark)) {
            setUnmarkCommand();
        } else if (checkTaskType(COMMAND_DELETE, delete)) {
            setDeleteCommand();
        } else if (checkTaskType(COMMAND_TODO, todo)) {
            setTodoCommand();
        } else if (checkTaskType(COMMAND_FIND, find)) {
            setFindCommand();
        } else if (checkTaskType(COMMAND_SIMPLIFY, simplify)) {
            setSimplifyCommand();
        } else if (checkTaskType(COMMAND_DEADLINE, deadline)) {
            setDeadlineCommand();
        } else if (checkTaskType(COMMAND_EVENT, event)) {
            setEventCommand();
        }
    }

    public void setShortCommand(String input) {

    }

    /**
     * Method to update variables for a Mark command
     */
    public void setMarkCommand() {
        setTaskType(COMMAND_MARK);
        ind = Integer.parseInt(rest);
    }

    /**
     * Method to update variables for a Unmark command
     */
    public void setUnmarkCommand() {
        setTaskType(COMMAND_UNMARK);
        ind = Integer.parseInt(rest);
    }

    /**
     * Method to update variables for a Delete command
     */
    public void setDeleteCommand() {
        setTaskType(COMMAND_DELETE);
        ind = Integer.parseInt(rest);
    }

    /**
     * Method to update variables for a Todo command
     */
    public void setTodoCommand() {
        setTaskType(COMMAND_TODO);
        desc = rest;
    }

    /**
     * Method to update variables for a Find command
     */
    public void setFindCommand() {
        setTaskType(COMMAND_FIND);
        desc = rest;
    }

    /**
     * Method to update variables for a Simplify command
     */
    public void setSimplifyCommand() {
        setTaskType(COMMAND_SIMPLIFY);
        initialCmd = Parser.getInitialCommand(rest);
        newCmd = Parser.getNewCommand(rest);
    }

    /**
     * Method to update variables for a Deadline command
     */
    public void setDeadlineCommand() {
        setTaskType(COMMAND_DEADLINE);
        String dateType = Parser.getDateType(rest);
        if (dateType.equals(NO_DATE_ERROR)) {
            date = NO_DATE_ERROR;
            desc = Parser.getDesc(rest);

        } else if (!(dateType.equals(DEADLINE_COMMAND))) {
            date = WRONG_DEADLINE_FORMAT;
            desc = Parser.getDesc(rest);

        } else {
            date = Parser.getDate(rest);
            desc = Parser.getDesc(rest);
        }
    }

    /**
     * Method to update variables for a Event command
     */
    public void setEventCommand() {
        setTaskType(COMMAND_EVENT);
        String dateType = Parser.getDateType(rest);
        if (dateType.equals(NO_DATE_ERROR)) {
            date = NO_DATE_ERROR;
            desc = Parser.getDesc(rest);

        } else if (!(dateType.equals(EVENT_COMMAND))) {
            date = WRONG_EVENT_FORMAT;
            desc = Parser.getDesc(rest);

        } else {
            date = Parser.getDate(rest);
            desc = Parser.getDesc(rest);
        }
    }

    /**
     * Returns the index of UserInput object.
     *
     * @return index of UserInput object.
     */
    public int getInd() {
        return ind;
    }

    /**
     * Returns the description of UserInput object.
     *
     * @return description of UserInput object.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the date of UserInput object.
     *
     * @return date of UserInput object.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the task type of UserInput object.
     *
     * @return task type of UserInput object.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns initial custom shortened command.
     *
     * @return initial custom shortened command
     */
    public String getInitialCmd() {
        return initialCmd;
    }

    /**
     * Returns new user defined shortened command.
     *
     * @return new user defined shortened command
     */
    public String getNewCmd() {
        return newCmd;
    }


    private void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Checks if user command task type is a valid command type
     *
     * @param taskTypeConst original task types
     * @param taskTypeSimplified simplified task types
     * @return true if user command is valid, false otherwise
     */
    private boolean checkTaskType(String taskTypeConst, String taskTypeSimplified) {
        return this.taskType.equals(taskTypeConst) || this.taskType.equals(taskTypeSimplified);
    }

    private boolean checkTaskType(String taskType, String taskTypeConst, String taskTypeSimplified) {
        return taskType.equals(taskTypeConst) || taskType.equals(taskTypeSimplified);
    }


    /**
     * Checks of string is a valid input to be simplified
     *
     * @param command User input command to be simplified
     * @return True if command to be simplified was an intitial command, false if not
     */
    public boolean isCommandType(String command) {
        boolean contains = Arrays.asList(commandList).contains(command);
        return contains;
    }

    /**
     * Simplifies the command from initialCmd to newCmd.
     */
    public static void setSimplifiedCommand() {
        switch (initialCmd) {
        case "mark":
            mark = newCmd;
            break;
        case "unmark":
            unmark = newCmd;
            break;
        case "delete":
            delete = newCmd;
            break;
        case "todo":
            todo = newCmd;
            break;
        case "find":
            find = newCmd;
            break;
        case "deadline":
            deadline = newCmd;
            break;
        case "event":
            event = newCmd;
            break;
        case "simplify":
            simplify = newCmd;
            break;
        case "list":
            list = newCmd;
            break;
        default:
            DobbyChat.unknown();
        }
    }
}
