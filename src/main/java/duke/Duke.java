package duke;

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
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    public static String getWelcome() {
        return Ui.showWelcome();
    }

    public String getResponse(String input) {
        String reply = "";
        Keyword keyword;
        String[] sections = input.split(" ", 2);

        try {
            keyword = this.parser.getKeyword(sections[0]);
        } catch (DukeException e) {
            return e.getMessage();
        }

        if (keyword != null) {
            switch (keyword) {
            case LIST:
                reply = this.tasks.toString();
                break;
            case MARK:
                reply = this.tasks.mark(this.parser.getIndex(sections[1]));
                this.storage.updateTasks(this.tasks.getList());
                break;
            case UNMARK:
                reply = this.tasks.unmark(this.parser.getIndex(sections[1]));
                this.storage.updateTasks(this.tasks.getList());
                break;
            case TODO:
                String description = sections[1];
                try {
                    reply = this.tasks.add(new Todo(description, false));
                    this.storage.updateTasks(this.tasks.getList());
                } catch (DukeException e) {
                    reply = e.getMessage();
                }
                break;
            case DEADLINE:
                reply = this.tasks.add(this.parser.createDeadline(sections[1]));
                this.storage.updateTasks(this.tasks.getList());
                break;
            case EVENT:
                reply = this.tasks.add(this.parser.createEvent(sections[1]));
                this.storage.updateTasks(this.tasks.getList());
                break;
            case DELETE:
                reply = this.tasks.delete(this.parser.getIndex(sections[1]));
                this.storage.updateTasks(this.tasks.getList());
                break;
            case FIND:
                reply = this.ui.showFoundTasks(this.tasks.findTasks(sections[1]));
                break;
            case BYE:
                this.storage.updateTasks(this.tasks.getList());
                reply = this.ui.showBye();
                break;
            default:
                break;
            }
        }
        return reply;
    }
}
