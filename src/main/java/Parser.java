public class Parser {
    private TaskList tasks;
    private Ui ui;
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }
    public void categorise(String str) throws DukeException{
        String uncap = str.toLowerCase();
        if (uncap.startsWith("delete")) {
            Integer i = Integer.parseInt(String.valueOf(str.charAt(7)));
            tasks.delete(i, ui);
        } else {
            if (!uncap.equals("list")) {
                if (!uncap.startsWith("mark") && !uncap.startsWith("unmark")) {
                    Task t = null;
                    if (uncap.startsWith("deadline")
                            || uncap.startsWith("event")) {
                        if (!uncap.contains("/")) this.ui.showTimeMissingError();
                        if (uncap.startsWith("deadline")) {
                            int idOfSlash = str.indexOf('/');
                            if (idOfSlash - 9 == 0) this.ui.showDescEmptyError("deadline");
                            if (str.length() < idOfSlash + 4) this.ui.showTimeMissingError();
                            t = new Deadline(str.substring(9, idOfSlash), str.substring(idOfSlash + 4));
                        } else if (uncap.startsWith("event")) {
                            int idOfSlash = str.indexOf('/');
                            if (idOfSlash - 6 == 0) this.ui.showDescEmptyError("event");
                            if (str.length() < idOfSlash + 4) this.ui.showTimeMissingError();
                            t = new Event(str.substring(6, idOfSlash), str.substring(idOfSlash + 4));
                        }
                    } else if (uncap.startsWith("todo")) {
                        if (str.length() < 6) this.ui.showDescEmptyError("todo");
                        t = new Todo(str.substring(5));
                    } else {
                        this.ui.showUnknownCommandError();
                    }
                    this.tasks.add(t);
                    this.ui.added(t);
                } else {
                    if (uncap.startsWith("unmark")) {
                        int i = Integer.parseInt(String.valueOf(uncap.charAt(7)));
                        tasks.unmark(i, ui);
                    } else if (uncap.startsWith("mark")) {
                        int i = Integer.parseInt(String.valueOf(uncap.charAt(5)));
                        tasks.mark(i, ui);
                    }
                }
            } else {
                ui.listOut(this.tasks.getArrayList());
            }
        }
    }
}
