public class ExitCmd extends Command {

    public ExitCmd(String cmd) {
        super(cmd);
    }

    @Override
    public void execute (String str, Task[] tasks) {
        String out = "Bye. Hope to see you again soon!";
        System.out.println(out);
        System.exit(0);
    }
}
