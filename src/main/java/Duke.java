import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.loadTasks());
    }

    public enum Keyword {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public void run() {
        this.ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        Keyword keyword;

        while (true) {
            try {
                keyword = this.parser.getKeyword(scanner.next());
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
                break;
            }
            if (keyword == Keyword.BYE) {
                break;
            } else if (keyword != null) {
                switch (keyword) {
                    case LIST:
                        this.ui.showTasks(this.tasks);
                        break;
                    case MARK:
                        this.tasks.mark(this.parser.getIndex(scanner));
                        break;
                    case UNMARK:
                        this.tasks.unmark(this.parser.getIndex(scanner));
                        break;
                    case TODO:
                        String description = scanner.nextLine();
                        if (description.length() > 0) {
                            description = description.substring(1);
                        }
                        try {
                            this.tasks.add(new Todo(description, false));
                        } catch (DukeException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    case DEADLINE:
                        this.tasks.add(this.parser.createDeadline(scanner.nextLine()));
                        break;
                    case EVENT:
                        this.tasks.add(this.parser.createEvent(scanner.nextLine()));
                        break;
                    case DELETE:
                        this.tasks.delete(this.parser.getIndex(scanner));
                        break;
                }
            } else {
                break;
            }
        }
        this.storage.updateTasks(this.tasks.getList());
        this.ui.showBye();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
