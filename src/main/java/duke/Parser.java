package duke;

import java.time.LocalDateTime;

import duke.task.*;


/**
 * Class containing parsing methods, or conversions.
 */
public class Parser {
    static final String TASK_TODO = " T ";
    static final String TASK_EVENT = " E ";
    static final String TASK_DEADLINE = " D ";
    static final String EXIT_COMMAND = "bye";
    static final String LIST_COMMAND = "list";
    static final String MARK_COMMAND = "mark";
    static final String UNMARK_COMMAND = "unmark";
    static final String DELETE_COMMAND = "delete";
    static final String TODO_COMMAND = "todo";
    static final String EVENT_COMMAND = "event";
    static final String DEADLINE_COMMAND = "deadline";
    static final String CLEARALL_COMMAND = "clear";
    static final String FIND_COMMAND = "find";
    static final String HELP_COMMAND = "help";
    static final int MIN_LENGTH = 6;
    static final int TODO_MIN_LENGTH = 3;
    static final int FIND_WORD_INDEX = 5;
    static final int INDEX_LOCATION = 2;
    static final int EVENT_SUBSTRING = 5;
    static final int DEADLINE_SUBSTRING = 8;
    static final int TODO_SUBSTRING = 4;
    private final Ui ui;
    private final TaskList tl;

    /**
     * Constructor for a new Parser.
     * @param ui Ui of the bot.
     * @param tl Tasklist to be worked on.
     */
    public Parser(Ui ui, TaskList tl) {
        this.ui = ui;
        this.tl = tl;
    }


    /**
     * Parses date and time string and converts it into a LocalDateTime.
     * @param dateTime string with date and time in format (dd/mm/yyyy hhmm).
     * @return LocalDateTime object containing date and time.
     */
    public static LocalDateTime parseDate(String dateTime) {
        String[] splitDateTime = dateTime.trim().split(" ");
        String[] dateArray = splitDateTime[0].split("/");
        String time = splitDateTime[1];

        return LocalDateTime.of(
                Integer.parseInt(dateArray[2]),
                Integer.parseInt(dateArray[1]),
                Integer.parseInt(dateArray[0]),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4))
        );
    }

    /**
     * Parses string containing task into a Task object.
     * @param task string containing task.
     * @return Task object.
     */
    public static Task fileStringToTask(String task) {
        String[] taskSplit = task.split("\\|");
        String type = taskSplit[0];
        switch (type) {
        case TASK_TODO:
            return new Todo(taskSplit[2].trim(), taskSplit[1].trim().equals("1"));
        case TASK_EVENT:
            return new Event(taskSplit[2].trim(), taskSplit[3], taskSplit[1].trim().equals("1"));
        case TASK_DEADLINE:
            return new Deadline(taskSplit[2].trim(), taskSplit[3], taskSplit[1].trim().equals("1"));
        default:
            return null;
        }
    }

    /**
     * Parses event inputs and returns an Event.
     * @param eventCommand String of event command input to be parsed.
     * @return new Event.
     */
    public static Event parseEventInput(String eventCommand) {
        int slashPos = eventCommand.indexOf("/at");
        String taskName = eventCommand.substring(EVENT_SUBSTRING, slashPos - 1) + " ";
        String deadline = eventCommand.substring(slashPos + 3);
        return new Event(taskName, deadline);
    }

    /**
     * Parses deadline inputs and returns a Deadline.
     * @param deadlineCommand String of deadline command input to be parsed.
     * @return new Deadline.
     */
    public static Deadline parseDeadlineInput(String deadlineCommand) {
        int slashPos = deadlineCommand.indexOf("/by");
        String taskName = deadlineCommand.substring(DEADLINE_SUBSTRING, slashPos - 1) + " ";
        String deadline = deadlineCommand.substring(slashPos + 3);
        return new Deadline(taskName, deadline);
    }

    /**
     * Parses a todo inputs and returns a todo.
     * @param todoCommand String of command.
     * @return a new Todo.
     */
    public static Todo parseTodoInput(String todoCommand) {
        String item = todoCommand.substring(TODO_SUBSTRING);
        return new Todo(item);
    }

    /**
     * Parses all inputs.
     * @param input Input from scanner.
     * @return String to be printed.
     */
    public String parseInput(String input) {
        String[] msgWords = input.split("\\b");
        String command = msgWords[0];
        int index;
        switch (command) {
        case EXIT_COMMAND:
            return "exit";
        case LIST_COMMAND:
            return (ui.printList(tl));
        case MARK_COMMAND:
            index = Integer.parseInt(msgWords[INDEX_LOCATION]) - 1;
            if (index < 1 || index > tl.size()) {
                return (ui.printOutOfBoundsMsg());
            }
            tl.mark(index);
            return ui.printMarkedMsg(tl.get(index));
        case UNMARK_COMMAND:
            index = Integer.parseInt(msgWords[INDEX_LOCATION]) - 1;
            if (index < 1 || index > tl.size()) {
                return ui.printOutOfBoundsMsg();
            }
            tl.unMark(index);
            return ui.printUnmarkedMsg(tl.get(index));
        case DELETE_COMMAND:
            index = Integer.parseInt(msgWords[INDEX_LOCATION]) - 1;
            Task removed = tl.get(index);
            tl.remove(index);
            return ui.printDeleteMsg(removed.toString(), tl.size());
        case TODO_COMMAND:
            if (msgWords.length < TODO_MIN_LENGTH) {
                return ui.printNoTaskInputMsg();
            }
            Todo newTodo = Parser.parseTodoInput(input);
            tl.add(newTodo);
            return ui.printTaskAddedMsg(newTodo, tl.size());
        case DEADLINE_COMMAND:
            if (msgWords.length < MIN_LENGTH || !input.contains("/by")) {
                return ui.printIncompleteDeadline();
            }
            Deadline newDL = Parser.parseDeadlineInput(input);
            tl.add(newDL);
            return ui.printTaskAddedMsg(newDL, tl.size());
        case EVENT_COMMAND:
            if (msgWords.length < MIN_LENGTH || !input.contains("/at")) {
                return ui.printIncompleteEvent();
            }
            Event newEvent = Parser.parseEventInput(input);
            tl.add(newEvent);
            return ui.printTaskAddedMsg(newEvent, tl.size());
        case CLEARALL_COMMAND:
            tl.clear();
            return ui.printClearMsg();
        case FIND_COMMAND:
            String words = input.substring(FIND_WORD_INDEX).trim();
            TaskList filteredTaskList = new TaskList(tl.findMatching(words));
            return ui.printFilteredList(filteredTaskList);
        case HELP_COMMAND:
            return ui.printHelpPage();
        default:
            return ui.printError();
        }
    }
}
