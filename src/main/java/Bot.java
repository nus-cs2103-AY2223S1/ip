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
        String command = split[0];
        String response = "";
        if (command.equals("todo") | command.equals("event") | command.equals("deadline")) {
            Task task = null;
            String name = "";
            for (int i=1; i<split.length; i++) {
                name = String.format("%s%s ", name, split[i]);
            }
            if (command.equals("todo")) {
                task = new Todo(name);
            } else if (command.equals("deadline")) {
                String[] details = name.split("\\s/by");
                task = new Deadline(details[0], details[1]);
            } else if (command.equals("event")) {
                String[] details = name.split("\\s/at");
                task = new Event(details[0], details[1]);
            }
            this.tasks.add(task);
            String header = "Got it. I've added this task:";
            String line = String.format("  %s", task.toString());
            String footer = String.format("Now you have %s task in the list", this.tasks.size());
            response = String.format("%s%s\n%s%s\n%s%s", SPACE, header, SPACE, line, SPACE, footer);
        } else if (split[0].equals("mark")) {
            Task task = this.tasks.get(Integer.parseInt(split[1])-1);
            task.mark();
            String header = "Nice! I've marked this task as done:";
            String line = String.format("  %s", task.toString());
            response = String.format("%s%s\n%s%s", SPACE, header, SPACE, line);
        } else if (split[0].equals("unmark")) {
            Task task = this.tasks.get(Integer.parseInt(split[1])-1);
            task.unmark();
            String header = "OK, I've marked this task as not done yet:";
            String line = String.format("  %s", task.toString());
            response = String.format("%s%s\n%s%s", SPACE, header, SPACE, line);
        } else if (input.equals("bye")) {
            String header = "Bye. Hope to see you again soon!";
            response = String.format("%s%s", SPACE, header);
        } else if (input.equals("list")) {
            String header = "Here are the tasks in your list";
            response = String.format("%s%s\n", SPACE, header);
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                String line = String.format("%s.%s", i + 1, task.toString());
                response = String.format("%s%s%s", response, SPACE, line);
                if (i < this.tasks.size() - 1) {
                    response = response.concat("\n");
                }
            }
        } else {
            response = "WRONG!!!!!";
        }
        System.out.println(String.format("%s\n%s\n%s\n", BORDER, response, BORDER));
        }
    }
