public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public void execute(String command, String description, TaskList tasks) {
        try {
            if (command.toLowerCase().equals("list")) {
                ui.listTasks(tasks);
            } else if (command.toLowerCase().equals("mark")) {
                int index = Integer.parseInt(description.substring(1)) - 1;
                tasks.mark(index);
            } else if (command.toLowerCase().equals("unmark")) {
                int index = Integer.parseInt(description.substring(1)) - 1;
                tasks.unmark(index);
            } else if (command.toLowerCase().equals("delete")) {
                int index = Integer.parseInt(description.substring(1)) - 1;
                tasks.delete(index);
            } else if (command.toLowerCase().equals("todo")) {
                if (description.isEmpty()) {
                    throw new DukeEmptyToDoException();
                }
                tasks.add(new ToDo(description));
            } else if (command.toLowerCase().equals("deadline")) {
                if(description.isEmpty()) {
                    throw new DukeEmptyDeadlineException();
                }
                String[] input = description.split(" /by ");
                tasks.add(new Deadline(input[0], input[1]));
            } else if (command.toLowerCase().equals("event")) {
                if(description.isEmpty()) {
                    throw new DukeEmptyEventException();
                }
                String[] input = description.split(" /at ");
                tasks.add(new Event(input[0], input[1]));
            } else if (command.toLowerCase().equals("help")) {
                ui.helpMessage();
            } else {
                throw new DukeInvalidCommandException();
            }
        } catch (DukeException e) {
            ui.printErr(e);
        } catch (Exception e) {
            ui.printErr("Input is invalid. Type help for list of available commands");
        }
    }


}
