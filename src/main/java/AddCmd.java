public class AddCmd extends Command {

    public AddCmd (String name){
        super(name);
    }
    private static int cnt = 0;

    @Override
    public void execute(String name, Task[] tasks) throws DukeException {
        Task newT;
        String str;
        String[] splitS;
        if (name.startsWith("todo")) {
            if (name.length() <= "todo ".length()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            str = name.substring("todo".length() + 1);
            newT = new Todo(str);
        } else if (name.startsWith("deadline")) {
            if (name.length() <= "deadline ".length()) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            str = name.substring("deadline".length() + 1);
            splitS = str.split("/by", 2);
            if (splitS[0].equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            if (splitS.length == 1) {
                throw new DukeException("The deadline of a task cannot be empty.");
            }
            newT = new Deadline(splitS[0], splitS[1]);
        } else if (name.startsWith("event")) {
            if (name.length() <= "event ".length()) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            str = name.substring("event".length() + 1);
            splitS = str.split("/at", 2);
            if (splitS[0].equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            if (splitS.length == 1) {
                throw new DukeException("The time of an event cannot be empty.");
            }

            newT = new Event(splitS[0], splitS[1]);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        tasks[cnt] = newT;
        cnt ++;

        String plural = cnt == 1
                ? "task"
                : "tasks";

        String out = "Got it. I've added this task:\n  " +
                newT + "\nNow you have "
                + cnt + " " + plural + " in the list.";

        System.out.println(out);
    }
}
