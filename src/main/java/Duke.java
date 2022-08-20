import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private ArrayList<Task> tasks;

    Duke() {
        tasks = new ArrayList<Task>();
    }

    private void startChatBot() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Eh hello, my name is Uncle Cheong. \n" +
                "What you want?\n");
        InputParser inputParser = new InputParser(sc, tasks);
        inputParser.parseInputs();
        System.out.println("Eh you leaving me so soon?");
    }

    public static void main(String[] args) {
        Duke uncleCheong = new Duke();
        uncleCheong.startChatBot();
    }
}
