import java.util.Scanner;
enum CommonPhrase {
    HELLO("Hello! I'm Duke\n" + "I'm ready to serve you!"),
    GOODBYE("Goodbye, Hope to see you soon!"),
    BOT_DIVIDER("~~~~~-----DUKE-----~~~~~\n"),
    USER_DIVIDER("~~~~~-----YOU-----~~~~~~\n");
    private final String text;

    CommonPhrase(String text) {
        this.text = text;
    }

    public String getPhrase() {
        return this.text;
    }
}

public class Duke {

    String botSpeak(String phrase) {
        return CommonPhrase.BOT_DIVIDER.getPhrase() + phrase + "\n"
                + CommonPhrase.USER_DIVIDER.getPhrase();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.print(duke.botSpeak(CommonPhrase.HELLO.getPhrase()));
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        Records lst = new Records();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.print(duke.botSpeak(lst.toString()));
            } else if (input.startsWith("mark")) {
                int taskIdx = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                Task updatedTask = lst.get(taskIdx).markDone();
                lst = lst.set(taskIdx, updatedTask);
                System.out.print(duke.botSpeak("Nice! this task is marked as done. Good Job!\n"
                        + updatedTask.toString()));
            } else if (input.startsWith("unmark")) {
                int taskIdx = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                Task updatedTask = lst.get(taskIdx).unmarkDone();
                lst = lst.set(taskIdx, updatedTask);
                System.out.print(duke.botSpeak("This task is marked as not done. Keep it up!\n"
                        + updatedTask.toString()));
            } else {
                lst = lst.add(new Task(input));
                System.out.print(duke.botSpeak("added: " + input));
            }
            input = scn.nextLine();
        }

        System.out.print(CommonPhrase.BOT_DIVIDER.getPhrase());
        System.out.println(CommonPhrase.GOODBYE.getPhrase());
        System.out.println("----------------------------------------");
    }
}
