import java.util.List;

public class Formatter {
    public String basic(String reply) {
        return addSeparator(reply);
    }

    public String added(String reply) {
        return addSeparator("I have added " + reply + " to the list!");
    }

    public <T extends String> String list(List<T> list) {
        StringBuilder reply = new StringBuilder();
        int count = 1;
        for (T item: list) {
            reply.append(count++ + ". " + item + "\n");
        }
        reply.setLength(reply.length() - 1);
        return addSeparator(reply.toString());
    }

    private String addSeparator(String reply) {
        String separator = "_______________________________________";
        return separator + "\n" + reply + "\n" + separator;
    }
}
