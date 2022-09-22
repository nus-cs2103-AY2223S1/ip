package duke;

import java.util.Scanner;

/**
 * Duke is a chatbot that records a list of Tasks that a user wishes to keep track of.
 */
class Duke {
    private TaskList tasks = new TaskList();

    /**
     * The main program.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        Storage.createDataFolder();
        Storage.createDataFile();
        Duke duke = new Duke();

        try {
            duke.tasks = Storage.initializeData();
        } catch (DukeException ex) {
            System.out.println("Error in initializing data!");
        }

        Ui.sayHello();

        while(true) {
            String action = duke.listen();

            if (Parser.containsExactKeyword(action)) {
                switch (action) {
                case Parser.EXACT_KEYWORD_BYE:
                    Ui.sayGoodbye();
                    break;
                case Parser.EXACT_KEYWORD_LIST:
                    duke.listTasks();
                    break;
                }

                if (action.equals(Parser.EXACT_KEYWORD_BYE)) {
                    break;
                }
            } else if (Parser.containsMarkKeyword(action)) {
                try {
                    int id = Parser.getSpecifier(action);

                    if (id > duke.tasks.size() || id < 1) {
                        throw new DukeException("Out of bounds index!");
                    }

                    switch (Parser.getNonexactKeyword(action)) {
                    case Parser.MARK_KEYWORD_MARK:
                        duke.markTask(duke, id);
                        break;
                    case Parser.MARK_KEYWORD_UNMARK:
                        duke.unmarkTask(duke, id);
                        break;
                    case Parser.MARK_KEYWORD_DELETE:
                        duke.tasks.deleteTask(id);
                        break;
                    }

                    Storage.updateData(duke.tasks);
                } catch (DukeException ex) {
                    Ui.echo("Index is out of bounds!");
                }
            } else if (Parser.containsTaskKeyword(action)) {
                try {
                    switch (Parser.getNonexactKeyword(action)) {
                    case Parser.TASK_KEYWORD_TODO:
                        duke.tasks.add(new Todo(action));
                        break;
                    case Parser.TASK_KEYWORD_DEADLINE:
                        if (!action.contains(Deadline.DELIMITER)) {
                            throw new DukeException("Missing Deadline delimiter!");
                        }
                        duke.tasks.add(new Deadline(action));
                        break;
                    case Parser.TASK_KEYWORD_EVENT:
                        if (!action.contains(Event.DELIMITER)) {
                            throw new DukeException("Missing Event delimiter!");
                        }
                        duke.tasks.add(new Event(action));
                        break;
                    }

                    Storage.updateData(duke.tasks);

                    Ui.echo("Added task:" +
                            duke.tasks.getLast().printTask());
                } catch (DukeException ex) {
                    Ui.echo("Incorrect use of " + Parser.getNonexactKeyword(action));
                }
            } else if (Parser.containsFindKeyword(action)) {
                duke.listMatchingTasks(action);
            } else {
                Ui.echo("I can't understand. Please enter a proper command, friend!");
            }
        }
    }

    /**
     * List matching tasks according to user search.
     *
     * @param userInput the user input.
     */
    public void listMatchingTasks(String userInput) {
        this.tasks.printMatchingTasks(Parser.getTermTofind(userInput));
    }

    /**
     * Lists all tasks recorded by the user.
     */
    public void listTasks() {
        this.tasks.listAllTasks();
    }

    /**
     * Marks a task as done.
     *
     * @param interaction this duke session.
     * @param id the id of the task.
     */
    public void markTask(Duke interaction, int id) {
        Task task = interaction.tasks.get(id - 1);
        task.markAsDone();
        Ui.echo(String.format("Task %d: [%s] marked as done!",
                id,
                task.getDescription()));
    }

    /**
     * Marks a task as not done.
     *
     * @param interaction this duke session.
     * @param id the id of the task.
     */
    public void unmarkTask(Duke interaction, int id) {
        Task task  = interaction.tasks.get(id - 1);
        task.markAsNotDone();
        Ui.echo(String.format("Task %d [%s] marked as not done!",
                id,
                task.getDescription()));
    }

    /**
     * Listens to the user input.
     *
     * @return the user input.
     */
    public String listen() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
