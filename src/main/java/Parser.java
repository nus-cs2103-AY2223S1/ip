public class Parser {
    TaskList tasks;

    public Parser(TaskList tl) {
        this.tasks = tl;
    }

    public boolean handler(String input) throws DukeException {
        String[] args = input.split(" ", 2);
        boolean end = false;

        switch (args[0]) {
            case "list":
                tasks.list();
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    tasks.listAdd(args[0], args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "delete":
                try {
                    tasks.listDelete(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            // mark is implemented as a toggle. note this.
            case "mark":
                try {
                    tasks.listToggle(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "bye":
                end = true;
                Ui.exit();
                break;
            default:
                throw new DukeUnknownInputException(args[0]);
        }
        return end;
    }
}
