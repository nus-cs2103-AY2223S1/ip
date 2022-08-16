public class GreetCmd extends Command {

    public GreetCmd(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(String str, Task[] tasks) {
        String out = "Hello! I'm Duke\n" +
                "What can I do for you?\n";
        System.out.println(out);
    }
}
