package Duck;
import Models.Todo;


public class UI {
    public static void printWelcomeMessage() {
        System.out.println("Hello! Got any grapes?");
    }
    public static void addNewItemMessage(Todo item) {
        System.out.println("Quack! Added new item: " + item);
    }
    public static void markItemMessage(Todo item) {
        System.out.println("Quack, marked! " + item);
    }
    public static void unmarkItemMessage(Todo item) {
        System.out.println("Quack, unmarked! " + item);
    }
    public static void deleteItemMessage(Todo item) {
        System.out.println("Quack, unmarked! " + item);
    }
    public static void unrecognizedCommandMessage() {
        System.out.println("Quack!?! What does that even mean!?!?!");
    }
    public static void printClosingMessage() {
        System.out.println("Quackbye!");
    }

}
