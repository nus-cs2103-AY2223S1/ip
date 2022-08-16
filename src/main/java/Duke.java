import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private ArrayList<Task> tasks;

    Duke() {
        tasks = new ArrayList<Task>();
    }

    private void printStoredInputs() {
        int numberOfTasks = this.tasks.size();
        if (numberOfTasks > 0) {
            System.out.println("Boss ah, this one your tasks:");
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            this.printTaskCountMessage();
        } else if (numberOfTasks == 0) {
            System.out.println("Boss, you got no task yet ah");
        }
    }

    private void addTask(Task input) {
        tasks.add(input);
    }

    private void startChatBot() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Eh hello, my name is Uncle Cheong. \n" +
                "What you want?\n");
        InputParser inputParser = new InputParser(sc, tasks);
        inputParser.parseInputs();
    }

    private void printTaskCountMessage() {
        System.out.printf("Boss, you got %s tasks now\n", this.tasks.size());
    }

    public static void main(String[] args) {
        String[] arr = "abc".split(" ", 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        Duke uncleCheong = new Duke();
        uncleCheong.startChatBot();
    }
}
