import java.util.LinkedList;
import java.util.Scanner;

public class Duke extends Chatbot {
    private static final String GREETING = "Hi friend! How may I help you?";
    private static final String FAREWELL = "See you soon, friend!";
    private LinkedList<Task> tasks = new LinkedList<>();

    public static void main(String[] args) {
        FrenIO.createDataFolder();
        FrenIO.createDataFile();
        Duke duke = new Duke();

        try {
            duke.tasks = FrenIO.initializeData();
        } catch (DukeException ex) {
            System.out.println("Error in initializing data!");
        }

        duke.sayHello();

        while(true) {
            String action = duke.listen();

            if (KeywordChecker.containsExactKeyword(action)) {
                switch (action) {
                    case KeywordChecker.EXACT_KEYWORD_BYE:
                        duke.sayGoodbye();
                        break;
                    case KeywordChecker.EXACT_KEYWORD_LIST:
                        duke.listTasks(duke);
                        break;
                }

                if (action.equals(KeywordChecker.EXACT_KEYWORD_BYE)) {
                    break;
                }
            } else if (KeywordChecker.containsMarkKeyword(action)) {
                try {
                    int id = KeywordChecker.getSpecifier(action);

                    if (id > duke.tasks.size() || id < 1) {
                        throw new DukeException("Out of bounds index!");
                    }

                    switch (KeywordChecker.getNonexactKeyword(action)) {
                        case KeywordChecker.MARK_KEYWORD_MARK:
                            duke.markTask(duke, id);
                            break;
                        case KeywordChecker.MARK_KEYWORD_UNMARK:
                            duke.unmarkTask(duke, id);
                            break;
                        case KeywordChecker.MARK_KEYWORD_DELETE:
                            duke.deleteTask(duke, id);
                            break;
                    }
                } catch (DukeException ex) {
                    duke.echo("Index is out of bounds!");
                }
            } else if (KeywordChecker.containsTaskKeyword(action)) {
                try {
                    switch (KeywordChecker.getNonexactKeyword(action)) {
                        case KeywordChecker.TASK_KEYWORD_TODO:
                            duke.tasks.add(new Todo(action));
                            break;
                        case KeywordChecker.TASK_KEYWORD_DEADLINE:
                            if (!action.contains(Deadline.DELIMITER)) {
                                throw new DukeException("Missing Deadline delimiter!");
                            }
                            duke.tasks.add(new Deadline(action));
                            break;
                        case KeywordChecker.TASK_KEYWORD_EVENT:
                            if (!action.contains(Event.DELIMITER)) {
                                throw new DukeException("Missing Event delimiter!");
                            }
                            duke.tasks.add(new Event(action));
                            break;
                    }

                    FrenIO.updateData(duke.tasks);

                    duke.echo("Added task:" +
                            duke.tasks.getLast().printTask());
                } catch (DukeException ex) {
                    duke.echo("Incorrect use of " + KeywordChecker.getNonexactKeyword(action));
                }
            } else {
                duke.echo("I can't understand. Please enter a proper command, friend!");
            }
        }
    }

    public void listTasks(Duke interaction) {
        interaction.tasks.forEach(
                task -> { System.out.println(String.format("%d.%s",
                        tasks.indexOf(task) + 1,
                        task.printTask()));
                });
    }

    public void markTask(Duke interaction, int id) {
        Task task = interaction.tasks.get(id - 1);
        task.markAsDone();
        interaction.echo(String.format("Task %d: [%s] marked as done!",
                id,
                task.getDescription()));
    }

    public void unmarkTask(Duke interaction, int id) {
        Task task  = interaction.tasks.get(id - 1);
        task.markAsNotDone();
        interaction.echo(String.format("Task %d [%s] marked as not done!",
                id,
                task.getDescription()));
    }

    public void deleteTask(Duke interaction, int id) {
        Task task = interaction.tasks.get(id - 1);
        interaction.tasks.remove(task);
        interaction.echo(String.format("Task %d [%s] removed.",
                id,
                task.getDescription()));
    }

    @Override
    public void echo(String message) {
        System.out.println(message);
    }

    @Override
    public void sayHello() {
        System.out.println(Duke.GREETING);
    }

    @Override
    public void sayGoodbye() {
        System.out.println(Duke.FAREWELL);
    }

    @Override
    public String listen() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
