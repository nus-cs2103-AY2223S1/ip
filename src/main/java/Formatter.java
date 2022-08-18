import java.util.List;

public class Formatter {
    public String basic(String reply) {
        return addSeparator(reply);
    }

    public String added(String reply) {
        return addSeparator("I have added " + reply + " to the list!");
    }

    public <T extends Task> String list(List<T> list) {
        StringBuilder reply = new StringBuilder();
        reply.append("Here are the tasks you have added.\n");
        int count = 1;
        for (T task: list) {
            reply.append(count++ + ". " + "["
                    + task.getStatusIcon() + "] "  + task + "\n");
        }
        reply.setLength(reply.length() - 1);
        return addSeparator(reply.toString());
    }

    public String markDone(Task task) {
        System.out.println("Yay! I have marked this task as done!");
        return "  [X] " + task;
    }

    public String markUndone(Task task) {
        System.out.println("Got it, I have marked this task as undone.");
        return "  [ ] " + task;
    }

    private String addSeparator(String reply) {
        String separator = "_______________________________________";
        return separator + "\n" + reply + "\n" + separator;
    }
}
