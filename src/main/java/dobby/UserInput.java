package dobby;

/**
 * UserInput is a class that deals with interactions with the user.
 */
public class UserInput {
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_SIMPLIFY = "simplify";
    private static final String NO_DATE_ERROR = "noDate";
    private static final String WRONG_DEADLINE_FORMAT = "wrongDeadline";
    private static final String WRONG_EVENT_FORMAT = "wrongEvent";
    private static final String DEADLINE_COMMAND = "by";
    private static final String EVENT_COMMAND = "at";
    private static String mark = "m";
    private static String unmark = "um";
    private static String delete = "del";
    private static String todo = "t";
    private static String find = "f";
    private static String deadline = "d";
    private static String event = "e";
    private static String simplify = "s";
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
                if (input.contains(COMMAND_SIMPLIFY)) {
                    setTaskType(COMMAND_SIMPLIFY);
                    initialCmd = "";
                    newCmd = "";
                } else {
                    taskType = input;
                }
            } else {
                taskType = Parser.getTaskType(input);
                rest = Parser.getRestOfCommand(input);
                if (checkTaskType(COMMAND_MARK) | checkTaskType(mark)) {
                    setTaskType(COMMAND_MARK);
                    ind = Integer.parseInt(rest);

                } else if (checkTaskType(COMMAND_UNMARK) | checkTaskType(unmark)) {
                    setTaskType(COMMAND_UNMARK);
                    ind = Integer.parseInt(rest);

                } else if (checkTaskType(COMMAND_DELETE) | checkTaskType(delete)) {
                    setTaskType(COMMAND_DELETE);
                    ind = Integer.parseInt(rest);

                } else if (checkTaskType(COMMAND_TODO) | checkTaskType(todo)) {
                    setTaskType(COMMAND_TODO);
                    desc = rest;

                } else if (checkTaskType(COMMAND_FIND) | checkTaskType(find)) {
                    setTaskType(COMMAND_FIND);
                    desc = rest;

                } else if (checkTaskType(COMMAND_SIMPLIFY) | checkTaskType(simplify)) {
                    setTaskType(COMMAND_SIMPLIFY);
                    initialCmd = Parser.getInitialCommand(rest);
                    newCmd = Parser.getNewCommand(rest);

                } else if (checkTaskType(COMMAND_DEADLINE) | checkTaskType(deadline)) {
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

                } else if (checkTaskType(COMMAND_EVENT) | checkTaskType(event)) {
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
            }
        } catch (java.lang.NumberFormatException e) {
            DobbyChat.noNumber();
        }
        return taskType;
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

    private boolean checkTaskType(String taskType) {
        return this.taskType.equals(taskType);
    }

    /**
     * Simplifies the command from initialCmd to newCmd.a
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
        default:
            DobbyChat.unknown();
        }
    }
}
