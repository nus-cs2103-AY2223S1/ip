import java.util.ArrayList;
import java.util.HashMap;

public class Bot {

    protected final String name;
    protected final ArrayList<Task> tasks;
    protected static final String SPACE = "    ";
    protected static final String BORDER = "    ____________________________________________________________";

    public Bot() {
        this.name = "Bocil";
        this.tasks = new ArrayList<Task>();
    }

    public void introduce() {
        String line1 = String.format("%sHello! I'm %s", SPACE, this.name);
        String line2 = String.format("%sWhat can I do for you?", SPACE);
        System.out.println(String.format("%s\n%s\n%s\n%s\n", BORDER, line1, line2, BORDER));
    }
    public void answer(String input) {
        String[] split =  input.split("\\s");
        String response = "";
        if (split[0].equals("mark") & split.length==2) {
            Task task = this.tasks.get(Integer.parseInt(split[1])-1);
            task.mark();
            String header = "Nice! I've marked this task as done:";
            String line = String.format("  [%s] %s", task.getStatus(), task.getName());
            response = String.format("%s%s\n%s%s", SPACE, header, SPACE, line);
        } else if (split[0].equals("unmark") & split.length==2) {
            Task task = this.tasks.get(Integer.parseInt(split[1])-1);
            task.unmark();
            String header = "OK, I've marked this task as not done yet:";
            String line = String.format("  [%s] %s", task.getStatus(), task.getName());
            response = String.format("%s%s\n%s%s", SPACE, header, SPACE, line);
        } else if (input.equals("bye")) {
            String header = "Bye. Hope to see you again soon!";
            response = String.format("%s%s", SPACE, header);
        } else if (input.equals("list")) {
            String header = "Here are the tasks in your list";
            response = String.format("%s%s\n", SPACE, header);
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                String line = String.format("%s.[%s] %s", i + 1, task.getStatus(), task.getName());
                response = String.format("%s%s%s", response, SPACE, line);
                if (i < this.tasks.size() - 1) {
                    response = response.concat("\n");
                }
            }
        } else {
            this.tasks.add(new Task(input));
            response = String.format("%sadded: %s", SPACE, input);
        }
        System.out.println(String.format("%s\n%s\n%s\n", BORDER, response, BORDER));
        }
    }
