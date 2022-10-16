package duke;

import javafx.application.Application;

/**
 * Duke is a chatbot that records a list of Tasks that a user wishes to keep track of.
 */
class Duke {
    private static TaskList tasks = new TaskList();

    /**
     * Starts the main program.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Storage.createDataFolder();
        Storage.createDataFile();

        try {
            Duke.tasks = Storage.initializeData();
        } catch (DukeException ex) {
            System.out.println("Error in initializing data!");
        }

        Application.launch(Ui.class, args);
    }

    /**
     * Processes entries from user input in GUI.
     *
     * @param entry the user input from GUI.
     * @return a reply to show up in the GUI.
     */
    public static String process(String entry) {
        String reply = "";

        if (Parser.containsExactKeyword(entry)) {
            switch (entry) {
            case Parser.EXACT_KEYWORD_BYE:
                reply = Ui.FAREWELL;
                break;
            case Parser.EXACT_KEYWORD_LIST:
                reply = Duke.listTasks();
                break;
            default:
                reply = Ui.CONFUSED;
            }
        } else if (Parser.containsMarkKeyword(entry)) {
            try {
                int id = Parser.getSpecifier(entry);

                if (id > Duke.tasks.size() || id < 1) {
                    throw new DukeException("Out of bounds index!");
                }

                switch (Parser.getNonexactKeyword(entry)) {
                case Parser.MARK_KEYWORD_MARK:
                    reply = Duke.markTask(id);
                    break;
                case Parser.MARK_KEYWORD_UNMARK:
                    reply = Duke.unmarkTask(id);
                    break;
                case Parser.MARK_KEYWORD_DELETE:
                    reply = Duke.tasks.deleteTask(id);
                    break;
                default:
                }

                Storage.updateData(Duke.tasks);
            } catch (DukeException ex) {
                reply = "Index is out of bounds!";
            }
        } else if (Parser.containsTaskKeyword(entry)) {
            switch (Parser.getNonexactKeyword(entry)) {
            case Parser.TASK_KEYWORD_TODO:
                Duke.tasks.add(new Todo(entry));
                break;
            case Parser.TASK_KEYWORD_DEADLINE:
                if (!Parser.matchesDeadlinePattern(entry)) {
                    return Ui.WRONG_USAGE_DEADLINE;
                }
                Duke.tasks.add(new Deadline(entry));
                break;
            case Parser.TASK_KEYWORD_EVENT:
                if (!Parser.matchesEventPattern(entry)) {
                    return Ui.WRONG_USAGE_EVENT;
                }
                Duke.tasks.add(new Event(entry));
                break;
            default:
            }

            Storage.updateData(Duke.tasks);

            reply = "Added task:"
                    + Duke.tasks.getLast().printTask();
        } else if (Parser.containsFindKeyword(entry)) {
            reply = listMatchingTasks(entry);
        } else if (Parser.containsArchiveKeyword(entry)) {
            Storage.archive(Duke.tasks);
            reply = "All tasks archived.";
        } else {
            reply = Ui.CONFUSED;
        }

        return reply;
    }

    /**
     * Lists matching tasks according to user search.
     *
     * @param userInput the user input.
     */
    public static String listMatchingTasks(String userInput) {
        return Duke.tasks.printMatchingTasks(Parser.getTermTofind(userInput));
    }

    /**
     * Lists all tasks recorded by the user.
     */
    public static String listTasks() {
        return Duke.tasks.listAllTasks();
    }

    /**
     * Marks a task as done.
     *
     * @param id the id of the task.
     */
    public static String markTask(int id) {
        String str = "";

        Task task = tasks.get(id - 1);
        task.markAsDone();
        str = String.format("Task %d: [%s] marked as done!",
                id,
                task.getDescription());

        return str;
    }

    /**
     * Marks a task as not done.
     *
     * @param id the id of the task.
     */
    public static String unmarkTask(int id) {
        String str;

        Task task = tasks.get(id - 1);
        task.markAsNotDone();
        str = String.format("Task %d [%s] marked as not done!",
                id,
                task.getDescription());

        return str;
    }

    /**
     * Clears all tasks and update the data file.
     */
    public static void clearTasks() {
        Duke.tasks = new TaskList();
        Storage.updateData(Duke.tasks);
    }
}
