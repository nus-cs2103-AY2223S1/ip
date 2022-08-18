import java.util.List;

public class Formatter {
    public String basic(String reply) {
        return addSeparator(reply);
    }

    public String addTask(Task task, int listLength) {
        String taskss = listLength > 1 ? " tasks" : " task";
        return addSeparator("Alright, I have added the following task to the list!\n"
                + task + "\nYou currently have " + listLength + taskss + ".");
    }

    public <T extends Task> String list(List<T> list) {
        StringBuilder reply = new StringBuilder();
        reply.append("Here are the tasks you have added:\n");
        int count = 1;
        for (T task: list) {
            reply.append(count++ + ". " + task + "\n");
        }
        reply.setLength(reply.length() - 1);
        return addSeparator(reply.toString());
    }

    public String markDone(Task task) {
        return "Yay! I have marked this task as done!\n" + task;
    }

    public String markUndone(Task task) {
        return "Got it, I have marked this task as undone.\n" + task;
    }

    public String invalid() {
        return "I'm afraid i can't help you with that.\nType \"help\" for the list of things I can do for you.";
    }

    private String addSeparator(String reply) {
        String separator = "_______________________________________";
        return separator + "\n" + reply + "\n" + separator;
    }
}
