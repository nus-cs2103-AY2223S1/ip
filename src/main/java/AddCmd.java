public class AddCmd extends Command {

    public AddCmd (String name){
        super(name);
    }
    private static int cnt = 0;

    @Override
    public void execute(String name, Task[] tasks) {
        Task newT;
        String str;
        String[] splitS;
        if (name.startsWith("todo")) {
            str = name.substring("todo".length() + 1);
            newT = new Todo(str);
        } else if (name.startsWith("deadline")) {
            str = name.substring("deadline".length() + 1);
            splitS = str.split("/by", 2);
            newT = new Deadline(splitS[0], splitS[1]);
        } else if (name.startsWith("event")) {
            str = name.substring("event".length() + 1);
            splitS = str.split("/at", 2);
            newT = new Event(splitS[0], splitS[1]);
        } else {
            newT = new Task(name);
        }

        tasks[cnt] = newT;
        cnt ++;

        String plural = cnt == 1
                ? "task"
                : "tasks";

        String out = "Got it. I've added this task:\n  " +
                newT.toString() + "\nNow you have "
                + cnt + " " + plural + " in the list.";

        System.out.println(out);
    }
}
