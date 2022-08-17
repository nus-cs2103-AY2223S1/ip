import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int numTask = 0;
    private static Scanner sc = new Scanner((System.in));
    public static void main(String[] args) {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot\n");
        String instruction = sc.next();
        while (instructionReader(instruction)) {
            instruction = sc.next();
        }
    }

    public static boolean instructionReader(String instruction) {
        if (instruction.equals("bye")) {
            System.out.println("Goodbye!");
            return false;
        } else {
            switch (instruction) {
                case "list" -> listOut();
                case "mark" -> tasks[sc.nextInt() - 1].markTask();
                case "unmark" -> tasks[sc.nextInt() - 1].unmarkTask();
                default -> addList(instruction + sc.nextLine());
            }
            return true;
        }
    }
    public static void addList(String userInput) {
            tasks[numTask] = new Task(userInput);
            numTask++;
            System.out.println("added: " + userInput + "\n");
    }

    public static void listOut() {
        for(int i = 0; i < numTask; i++) {
            int num = i + 1;
            String header = num + ". ";
            System.out.println(header + tasks[i].toString());
        }
        System.out.println("");
    }
}
