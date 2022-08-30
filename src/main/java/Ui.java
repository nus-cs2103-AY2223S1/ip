import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner INPUT = new Scanner(System.in);

    public void welcome(){
        System.out.println("Hello! I'm THE BRO\n" + "What can I do for you?");
    }

    public String readCommand() {
        return INPUT.nextLine();
    }

    public static void listSize(ArrayList<Task> list1){
        System.out.println("You have " + list1.size() + " tasks left!");
    }

    public static void printAdd(Task t){
        System.out.println(t.toString());
    }

    public static void markUi(ArrayList<Task> list1, int n){
        System.out.println("I have marked this task\n" + (list1.get(n-1)).toString());
    }

    public static void unmarkUi(ArrayList<Task> list1, int n){
        System.out.println("I have unmarked this task\n" + (list1.get(n-1)).toString());
    }

    public static void deleteUi(ArrayList<Task> list1, int n){
        System.out.println("I have removed this task.\n" + (list1.get(n-1)).toString());
    }

    public static void bye(){
        System.out.println("See you later broo!");
    }

    public static void showLoadingError(){
        System.out.println("The file couldn't be loaded.");
    }

    public static void errorMessage(String msg){
        System.out.println(msg);
    }

}
