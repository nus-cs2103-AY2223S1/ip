import java.util.Scanner;
enum CommonPhrase {
    HELLO("Hello! I'm Duke\n" + "I'm ready to serve you!"),
    GOODBYE("Goodbye, Hope to see you soon!"),
    BOTDIVIDER("~~~~~-----DUKE-----~~~~~\n"),
    USERDIVIDER("~~~~~-----YOU-----~~~~~~\n");
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
        return CommonPhrase.BOTDIVIDER.getPhrase() + phrase + "\n"
                + CommonPhrase.USERDIVIDER.getPhrase();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.print(duke.botSpeak(CommonPhrase.HELLO.getPhrase()));
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        Records list = new Records();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.print(duke.botSpeak(list.toString()));
            } else {
                list = list.add(new Task(input));
                System.out.print(duke.botSpeak("added: " + input));
            }
            input = scn.nextLine();
        }

        System.out.print(CommonPhrase.BOTDIVIDER.getPhrase());
        System.out.println(CommonPhrase.GOODBYE.getPhrase());
        System.out.println("----------------------------------------");
    }
}
