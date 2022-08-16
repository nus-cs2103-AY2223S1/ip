import java.util.Scanner;
enum CommonPhrase {
    HELLO("Hello! I'm Duke\n" + "I'm ready to serve you!"),
    GOODBYE("Goodbye, Hope to see you soon!"),
    BOTDIVIDER("~~~~~-----DUKE-----~~~~~\n"),
    USERDIVIDER("~~~~~-----YOU-----~~~~~~\n");
    private String text;

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
        Echo echo = new Echo(scn.nextLine());
        while (!echo.toString().equals("bye")) {
            System.out.print(duke.botSpeak(echo.toString()));
            echo = new Echo(scn.nextLine());
        }
        System.out.print(CommonPhrase.BOTDIVIDER.getPhrase());
        System.out.println(CommonPhrase.GOODBYE.getPhrase());
        System.out.println("----------------------------------------");
    }
}
