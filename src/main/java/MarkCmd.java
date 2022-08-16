public class MarkCmd extends Command {

    private int num;
    public MarkCmd (String cmd, int num) {
        super(cmd);
        this.num = num;
    }

    @Override
    public void execute(String str, Tasks[] tasks) {
        tasks[this.num].markT();
        String out = "Nice! I've marked this task as done:! \n" +
                tasks[this.num].toString();
        System.out.println(out);
    }
}
