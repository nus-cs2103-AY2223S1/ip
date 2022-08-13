import java.util.*;
public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] taskArr = new String[100];
        int taskCounter = 0;
        String input = "";
        System.out.print("Hi I'm catBot!\nHow can I help you nya?\n");

        while (!input.equals("bye")) {
            input = sc.nextLine();
            switch(input) {
                case "bye":
                    break;
                case "list":
                    for (int i = 0; i < taskArr.length; i++) {
                        if (taskArr[i] != null) {
                            System.out.println((i + 1) + ". " + taskArr[i]);
                        } else {
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Roger nya! Added " + input + ".");
                    taskArr[taskCounter++] =  input;
                    break;
            }
        }

        System.out.print("Bye nya! Hope to see you again nya.");

    }
}
