public class ListCmd extends Command {

    public ListCmd (String cmd) {
        super(cmd);
    }

    @Override
    public void execute (String str, Task[] tasks) {
        System.out.println("Your List \n");
        for (int i = 0; i < tasks.length; i ++) {
            if (tasks[i] == null) {
                break;
            }
            int index = i + 1;
            System.out.println(index + "." + tasks[i].toString());
        }
    }
}
