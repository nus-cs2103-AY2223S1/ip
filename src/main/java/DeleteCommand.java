public class DeleteCommand extends Command {
    private int num;

    public DeleteCommand(int num) {
        super();
        this.num = num;
    }

    @Override
    public void execCommand(TaskList list) {
        Task removed = list.removeTask(this.num);
        System.out.println("Understood. I have removed this task:\n" + removed +
                "\nYou have " + list.getSize() + " task(s) in the list.");
    }
}
