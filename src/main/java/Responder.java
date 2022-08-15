import java.util.ArrayList;
import java.util.List;

public class Responder {

    List<String> tasks;

    public Responder() {
        tasks = new ArrayList<>();
        System.out.println(
            formatResponse("HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?"));
    }

    public void close() {
        System.out.println(formatResponse("GOODBYE!"));
    }

    public void echo(String input) {
        System.out.println(formatResponse(input));
    }

    public void addToList(String input) {
        tasks.add(input);
        System.out.println(
            formatResponse("OK. I ADDED \"" + input + "\" TO MY LIST."));
    }

    public void getList() {
        System.out.println(formatList());
    }

    private String formatList() {
        StringBuilder sb = new StringBuilder();
        sb.append(
            "____________________________________________________________");
        sb.append("\n \uD83E\uDD16: \n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(" ").append(i).append(". ").append(tasks.get(i - 1))
              .append("\n");
        }
        sb.append(
            "____________________________________________________________");
        return sb.toString();
    }

    private String formatResponse(String input) {
        return "____________________________________________________________" +
               "\n \uD83E\uDD16: " + input + "\n" +
               "____________________________________________________________";
    }
}
