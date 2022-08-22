public class ToDoCommand extends Command {
    private ToDo todo;

    public ToDoCommand(String description) {
        super();
        this.todo = new ToDo(description);
    }

    @Override
    public void execCommand(TaskList list, Save save) {
        list.addTask(this.todo);
        System.out.println("Successfully added new task:\n" + this.todo +
                "\nYou have " + list.getSize() + " task(s) in the list.");
        save.saveList(list.save());
    }
}
