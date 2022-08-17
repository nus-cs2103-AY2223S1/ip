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

    public Task getTask(int taskNumber) throws DukeException {
        if (taskNumber < 0 || taskNumber > this.taskList.size() - 1) {
            throw new DukeException("You do not have that task. Try seeing your task list instead!\n");
        }
        return this.taskList.get(taskNumber);
    }

}
