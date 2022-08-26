public class AddCommand extends Command {
    private String type;
    private String description;
    private String date;

    public AddCommand(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.type.equals("todo")) {
            Todo t = new Todo(description);
            taskList.add(t);
            ui.showAdd(t, taskList.getTaskArrayList().size());
        } else if (this.type.equals("deadline")) {
            Deadline d = new Deadline(this.description, this.date);
            taskList.add(d);
            ui.showAdd(d, taskList.getTaskArrayList().size());
        } else  {
            Event e = new Event(this.description, this.date);
            taskList.add(e);
            ui.showAdd(e, taskList.getTaskArrayList().size());
        }
    }
}
