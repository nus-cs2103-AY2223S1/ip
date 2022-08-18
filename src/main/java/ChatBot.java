public class ChatBot {
    public static final String NAME = "Duke";
    private Command command;
    public ChatBot(){
        String greetingMessage = "Hello! I'm " + NAME + "\n"
                + "What can I do for you?\n";
        System.out.println(greetingMessage);
        command = new Command();
    }

    public void echo (String input) {
        if ("bye".equalsIgnoreCase(input)) {
            System.out.println("Bye. Hope to see you again soon!\n");
            System.exit(0);
        } else if ("list".equalsIgnoreCase(input)) {
            command.printList();
        } else {
            command.addCommand(input);
            System.out.println(NAME + ": Added: " + input + "\n");
        }
    }
}
