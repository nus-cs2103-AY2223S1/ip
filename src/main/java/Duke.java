import java.util.LinkedList;
import java.util.Scanner;

public class Duke extends Chatbot {
    private static final String GREETING = "Hi friend! How may I help you?";
    private static final String FAREWELL = "See you soon, friend!";
    private LinkedList<Task> tasks = new LinkedList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
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
                switch (KeywordChecker.getNonexactKeyword(action)) {
                    case KeywordChecker.MARK_KEYWORD_MARK:
                        duke.markTask(duke, KeywordChecker.getSpecifier(action));
                        break;
                    case KeywordChecker.MARK_KEYWORD_UNMARK:
                        duke.unmarkTask(duke, KeywordChecker.getSpecifier(action));
                        break;
                }
            } else if (KeywordChecker.containsTaskKeyword(action)) {
                switch (KeywordChecker.getNonexactKeyword(action)) {
                    case KeywordChecker.TASK_KEYWORD_TODO:
                        duke.tasks.add(new Todo(action));
                        break;
                    case KeywordChecker.TASK_KEYWORD_DEADLINE:
                        duke.tasks.add(new Deadline(action));
                        break;
                    case KeywordChecker.TASK_KEYWORD_EVENT:
                        duke.tasks.add(new Event(action));
                        break;
                }

                duke.echo("added the task:" +
                        duke.tasks.getLast().printTask());
            } else {
                duke.tasks.add(new Task(action));
                duke.echo(String.format("added: %s", action));
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
        this.echo(String.format("Task %d: [%s] marked as done!",
                id,
                task.getDescription()));
    }

    public void unmarkTask(Duke interaction, int id) {
        Task task  = interaction.tasks.get(id - 1);
        task.markAsNotDone();
        this.echo(String.format("Task %d [%s] marked as not done!",
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
