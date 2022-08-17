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

    public ArrayList<Task> taskList = new ArrayList<>();

    public List() {
        System.out.println(greet);
    }

    public void addTask(Task command) {
        taskList.add(command);
    }

}
