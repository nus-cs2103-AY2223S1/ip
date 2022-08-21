import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private ArrayList<Task> tasks;

    Duke() {
        tasks = new ArrayList<Task>();
    }

    private void startChatBot() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Eh hello, my name is Uncle Cheong. \n" +
                    "What you want?\n");
            TasksReader tasksReader = new TasksReader();
            tasks = tasksReader.readSavedTasks();
            Parser parser = new Parser(sc, tasks);
            parser.parseInputs();
            TasksWriter tasksWriter = new TasksWriter(tasks);
            tasksWriter.writeToFile();
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.println("Eh you leaving me so soon?");
    }

    public static void main(String[] args) {
        Duke uncleCheong = new Duke();
        uncleCheong.startChatBot();
    }
}
