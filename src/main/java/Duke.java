import java.util.Scanner;

public class Duke {
    private final String name;

    public Duke(String name) {
        this.name = name;
    }

    private void displayMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }

    private String greetMessage() {
        String logo = " ,-----.,--.               ,--.    ,--.                  \n\t"
                    + "'  .--./|  ,---.  ,--,--.,-'  '-.,-'  '-.,--.,--. ,---.  \n\t"
                    + "|  |    |  .-.  |' ,-.  |'-.  .-''-.  .-'|  ||  |(  .-'  \n\t"
                    + "'  '--'\\|  | |  |\\ '-'  |  |  |    |  |  '  ''  '.-'  `) \n\t"
                    + " `-----'`--' `--' `--`--'  `--'    `--'   `----' `----'  \n\t";
        String message = "Hello! My name is " + this.name + ".\n\t" + "What can I do for you? :)";
        return logo + message;
    }

    private String byeMessage() {
        return "Bye! Till we next meet!";
    }

    public void appLoop() {
        Scanner input = new Scanner(System.in);
        String currentMessage = this.greetMessage();

        while(!currentMessage.equals("bye")) {
            displayMessage(currentMessage);
            currentMessage = input.nextLine();
        }
        displayMessage(this.byeMessage());
    }

    public static void main(String[] args) {
        Duke chattus = new Duke("Chattus");
        chattus.appLoop();
    }
}
