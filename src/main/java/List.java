import java.util.*;

public class List {
    private static final String greet =
            "OMG HII! I am \n" +
                    "███████╗██╗░░░░░░█████╗░██████╗░███████╗███╗░░██╗\n" +
                    "██╔════╝██║░░░░░██╔══██╗██╔══██╗██╔════╝████╗░██║\n" +
                    "█████╗░░██║░░░░░██║░░██║██████╔╝█████╗░░██╔██╗██║\n" +
                    "██╔══╝░░██║░░░░░██║░░██║██╔══██╗██╔══╝░░██║╚████║\n" +
                    "██║░░░░░███████╗╚█████╔╝██║░░██║███████╗██║░╚███║\n" +
                    "╚═╝░░░░░╚══════╝░╚════╝░╚═╝░░╚═╝╚══════╝╚═╝░░╚══╝\n" +
                    "What can I do for you?\n";

    public ArrayList<String> taskList = new ArrayList<>();

    public List() {
        System.out.println(greet);
    }

    public void addTask(String command) {
        taskList.add(command);
    }
}
