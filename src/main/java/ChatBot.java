import java.util.Scanner;

public class ChatBot {
    private final String name;

    public ChatBot(String name) {
        this.name = name;
    }

    private void echo(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }

    private String getGreetMessage() {
        String logo = " ,-----.,--.               ,--.    ,--.                  \n\t"
                + "'  .--./|  ,---.  ,--,--.,-'  '-.,-'  '-.,--.,--. ,---.  \n\t"
                + "|  |    |  .-.  |' ,-.  |'-.  .-''-.  .-'|  ||  |(  .-'  \n\t"
                + "'  '--'\\|  | |  |\\ '-'  |  |  |    |  |  '  ''  '.-'  `) \n\t"
                + " `-----'`--' `--' `--`--'  `--'    `--'   `----' `----'  \n\t";
        String message = "Hello! My name is " + this.name + ".\n\t" + "What can I do for you? :)";
        return logo + message;
    }

    private void exit() {
        String message = "Bye! Till we next meet!";
        this.echo(message);
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        String currentMessage = this.getGreetMessage();

        while(!currentMessage.equals("bye")) {
            this.echo(currentMessage);
            currentMessage = input.nextLine();
        }

        exit();
    }
}
