package duke;

import java.util.LinkedList;
import java.util.Scanner;

public class Duke extends Chatbot {
    //private LinkedList<Task> tasks = new LinkedList<>();
    private TaskList tasks = new TaskList();

    public static void main(String[] args) {
        new Duke().run();
    }

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
                        duke.listTasks(duke);
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
                            duke.deleteTask(duke, id);
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
            } else {
                Ui.echo("I can't understand. Please enter a proper command, friend!");
            }
        }
    }

    public void listTasks(Duke interaction) {
        this.tasks.listAllTasks();
    }

    public void markTask(Duke interaction, int id) {
        Task task = interaction.tasks.get(id - 1);
        task.markAsDone();
        Ui.echo(String.format("Task %d: [%s] marked as done!",
                id,
                task.getDescription()));
    }

    public void unmarkTask(Duke interaction, int id) {
        Task task  = interaction.tasks.get(id - 1);
        task.markAsNotDone();
        Ui.echo(String.format("Task %d [%s] marked as not done!",
                id,
                task.getDescription()));
    }

    public void deleteTask(Duke interaction, int id) {
        Task task = interaction.tasks.get(id - 1);
        interaction.tasks.remove(task);
        Ui.echo(String.format("Task %d [%s] removed.",
                id,
                task.getDescription()));
    }

    @Override
    public String listen() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
