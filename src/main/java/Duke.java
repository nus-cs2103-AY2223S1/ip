import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Storage myStorage = new Storage();
        String HORIZONTAL_LINE = "----------------------";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        String userInput = sc.nextLine();
        String[] userInputS = userInput.split(" ");

        while (!userInputS[0].equals("bye")) {
            if (userInputS[0].equals("list")) {
                myStorage.printStorage();
            } else if (userInputS[0].equals("mark")) {
                myStorage.markDone(Integer.parseInt(userInputS[1]));
                System.out.println(HORIZONTAL_LINE);
            } else {
                Task newInput = new Task(userInput);
                myStorage.addTask(newInput);
                System.out.println(HORIZONTAL_LINE);
            }
            userInput = sc.nextLine();
            userInputS = userInput.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
