public class UnmarkCmd extends Command {
    private int num;
    public UnmarkCmd (String cmd, int num) {
        super(cmd);
        this.num = num;
    }

    @Override
    public void execute(String str, Tasks[] tasks) {
        tasks[this.num].unmarkT();
        String out = "Nice! I've marked this task as done:! \n" +
                tasks[this.num].toString();
        System.out.println(out);
    }
}
