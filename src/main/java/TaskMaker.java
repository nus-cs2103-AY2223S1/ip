import java.util.StringTokenizer;

public class TaskMaker {

    public TaskMaker() {
    }

    public Task make(String input) {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken();
        System.out.println(type);
        String description = " " + tokens.nextToken();
        String deadline = "";
        while (tokens.hasMoreTokens()) {
            String curr = tokens.nextToken();
            System.out.println(curr);
            if (curr.equals("/by") | curr.equals("/at")) {
                while (tokens.hasMoreTokens()) {
                    deadline += " " + tokens.nextToken();
                }
            } else {
                description += " " + curr;
                System.out.println("hi");
            }
        }
        if (type.equals("todo")) {
            return new Todo(description);
        } else if (type.equals("deadline")) {
            return new Deadline(description, deadline);
        } else {
            return new Event(description, deadline);
        }
    }
}
