import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in).useDelimiter("\\n");

    public void welcomeUser() {
        String banner = "~~~~~~~~~~~\n"
                + " TED |._.|\n"
                + "~~~~~~~~~~~\n";
        System.out.println(banner);
        System.out.println("Hello! I'm Ted and I'm here to help you keep track of your tasks |._.|\n"
                + "How can I assist you today?\n");
    }

    public void tedResponse(String filler) {
        System.out.println("~ |._.| ~\n" + filler + "~\n");
    }

    public void showExit() {
        System.out.println("Goodbye! Have a pleasant day |._.|");
        sc.close();
    }

    public void deleteResponse(String task, int size) {
        tedResponse("Done! Task deleted:\n" + task + "\nremaining task count: " + size + "\n");
    }

    public void markResponse(String task) {
        tedResponse("Great! Task done:\n" + task + "\n");
    }

    public void unmarkResponse(String task) {
        tedResponse("Aw :( Task undone:\n" + task + "\n");
    }

    public void addResponse(String task, int size) {
        tedResponse("added to tasklist:\n" + task + "\ntask count: " + size + "\n");
    }

    public String readCommand() {
        String command = "";
        if (sc.hasNext()) {
            command = sc.next();
        }
        return command;
    }
}
