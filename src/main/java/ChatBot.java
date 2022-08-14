import java.util.Scanner;

public class ChatBot {
    private final String name;

    public ChatBot(String name) {
        this.name = name;
    }

    private void echo(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.print(message);
        System.out.println("\t____________________________________________________________");
    }

    private void greet() {
        String logo = "\t" + " ,-----.,--.               ,--.    ,--.                  " + "\n"
                + "\t" + "'  .--./|  ,---.  ,--,--.,-'  '-.,-'  '-.,--.,--. ,---.  " + "\n"
                + "\t" + "|  |    |  .-.  |' ,-.  |'-.  .-''-.  .-'|  ||  |(  .-'  " + "\n"
                + "\t" + "'  '--'\\|  | |  |\\ '-'  |  |  |    |  |  '  ''  '.-'  `) " + "\n"
                + "\t" + " `-----'`--' `--' `--`--'  `--'    `--'   `----' `----'  " + "\n";
        String message = "\n\t" + "Hello! My name is " + this.name
                    + "." + "\n\t" + "What can I do for you? :)" + "\n";
        this.echo(logo + message);
    }

    private void exit() {
        String message = "\t" + "Bye! Till we next meet!" + "\n";
        this.echo(message);
    }

    public void start() {
        this.greet();

        String currentMessage;
        Scanner input = new Scanner(System.in);
        ChatList list = new ChatList();

        while(true) {
            currentMessage = input.nextLine();

            if (currentMessage.equals("bye")) {
                this.exit();
                break;
            } else if (currentMessage.equals("list")) {
                this.echo(list.toString());
            } else {
                list.add(currentMessage);
                this.echo("\t" + "added: " + currentMessage + "\n");
            }
        }
    }
}
