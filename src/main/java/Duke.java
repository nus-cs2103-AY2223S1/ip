import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String topWindow = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n \t";
    private static final String bottomWindow = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String greeting = "Hi, I'm Ploopy! Nice to meet you!\n\tWhats up?";
    private static final String farewell = "Okay then, see ya later :)";
    private static final String completedTask = "Nice! You've completed this task. I'll mark it as done.";
    private static final String incompleteTask = "Alright this task has been marked as undone.";


    public static final String textArt = "\n" +
            "██████████h█████████████████████████\n" +
            "█▄─▄▄─█▄─▄███─▄▄─█─▄▄─█▄─▄▄─█▄─█─▄█\n" +
            "██─▄▄▄██─██▀█─██─█─██─██─▄▄▄██▄─▄██\n" +
            "▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▄▀▄▄▄▀▀▀▀▄▄▄▀▀";

    private static ArrayList<Task> tasks;

    public static void start() {
        messageFormatter(textArt);
        messageFormatter(greeting);
        tasks = new ArrayList<>();
        command();
    }

    private static void messageFormatter(String input) {
        System.out.println(topWindow + input + bottomWindow);
    }

    private static void displayList() {
        int index = 1;
        System.out.println(topWindow);
        for (Task item : tasks) {
            System.out.println("\n \t" + index + ". " + item.getStatus() + item);
            index++;
        }
        System.out.println(bottomWindow);
    }

    private static void markTask(int taskIndex) {
        Task current = tasks.get(taskIndex - 1);
        current.markDone();
        messageFormatter(completedTask + "\n\t" + current.getStatus() + " " + current);
    }

    private static void unmarkTask(int taskIndex) {
        Task current = tasks.get(taskIndex - 1);
        current.unmark();
        messageFormatter(incompleteTask + "\n\t" + current.getStatus() + " " + current);
    }

    private static void command() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder(scanner.nextLine());
        while(!input.toString().equals("bye")) {
            if (input.length() > 7) {
                if (input.substring(0, 7).equals("unmark ")) {
                    unmarkTask(input.charAt(7) - 48);
                }
            } else if (input.length() > 5) {
                if (input.substring(0, 5).equals("mark ")) {
                    markTask(input.charAt(5) - 48);
                }
            } else if (input.toString().equals("list")) {
                displayList();
            } else {
                tasks.add(new Task(input.toString()));
                messageFormatter("added: " + input);
            }
            input.replace(0, input.length(), scanner.nextLine()); //replacing content
        }
        messageFormatter(farewell);
    }
    
    public static void main(String[] args) {
        start();
    }
}
