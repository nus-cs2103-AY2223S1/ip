public class ChatBot {
    public static final String NAME = "Duke";
    public ChatBot(){
        String greetingMessage = "Hello! I'm " + NAME + "\n"
                + "What can I do for you?\n";
        System.out.println(greetingMessage);
    }

    public String echo (String input) {
        if ("bye".equalsIgnoreCase(input)) {
            System.out.println("Bye. Hope to see you again soon!\n");
            return input;
        }
        System.out.println(NAME + ":" + input + "\n");
        return input;
    }
}
