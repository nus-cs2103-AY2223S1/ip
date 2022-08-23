package duke;
public class Parser {
    private static Ui ui = new Ui();
    public static void parse(String command, TaskList tasks) throws DukeException {
        if (command.equals("list")) {
            ui.printMessage(tasks.toString());
        } else if (command.startsWith("mark")) {
            String str = command.replace("mark ", "");
            int index = Integer.valueOf(str);
            tasks.mark(index);
            ui.printMessage("Nice! I've marked this task as done:\n" + tasks.getString(index));
        } else if (command.startsWith("unmark")) {
            String str = command.replace("unmark ", "");
            int index = Integer.valueOf(str);
            tasks.unmark(index);
            ui.printMessage("Nice! I've marked this task as done:\n" + tasks.getString(index));
        } else if (command.startsWith("todo")) {
            String str = command.replace("todo", "");
            tasks.add(str, Duke.Type.TODO);
        } else if (command.startsWith("deadline")) {
            tasks.add(command, Duke.Type.DEADLINE);
        } else if (command.startsWith("event")) {
            tasks.add(command, Duke.Type.EVENT);
        } else if (command.startsWith("delete")) {
            tasks.delete(command);
        } else if (command.startsWith("find")) {
            String keyword = command.replace("find ", "");
            tasks.find(keyword);
        } else {
            throw new UnknownInputException();
        }
    }
}
