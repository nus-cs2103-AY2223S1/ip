public class Prompt {
    public static void startPrompt() {
        String logo = "Yi Xian";
        System.out.println("Hi from\n" + logo);
        listValidInstructions();
        lineDivider();
    }

    public static void listValidInstructions() {
        System.out.println("What can I do for you?");
        System.out.println("- todo (task name)");
        System.out.println("- deadline (task name) \\by (date)");
        System.out.println("- event (task name) \\at (date)");
        System.out.println("- list");
        System.out.println("- check (index)");
        System.out.println("- uncheck (index)");
        System.out.println("- delete (index)");
        System.out.println("- bye");
    }

    public static void endPrompt() {
        String goodbye_text = "Goodbye";
        System.out.println(goodbye_text);
    }

    public static void invalidInstruction() {
        System.out.println("Please give me a valid instruction");
    }

    public static void lineDivider() {
        System.out.println("-------------------------------------------------------------");
    }
}
