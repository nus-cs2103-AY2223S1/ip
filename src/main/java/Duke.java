import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm SoCCat \nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        String currInput = input.nextLine();
        Task[] userInputs = new Task[100];
        int index = 1;

        while (!currInput.equals("bye")) {
            if (currInput.equals("list")) {
                for (int i = 1; i < index; i++) {
                    System.out.println(i + ".[" + userInputs[i].getStatusIcon() + "] " + userInputs[i].getDescription());
                }
            }  else {
                String[] words = currInput.split(" ", 2);
                if (words.length < 2) {
                    System.out.println("added: " + currInput);
                    userInputs[index] = new Task(currInput);
                    index++;
                } else {
                    int taskIndex = Integer.parseInt(words[1]);

                    if (words[0].equals("mark")) {
                        if (taskIndex <= index) {
                            System.out.println(userInputs[taskIndex].mark());
                        }
                    } else if (words[0].equals("unmark")) {
                        if (taskIndex <= index) {
                            System.out.println(userInputs[taskIndex].unmark());
                        }
                    } else {
                        System.out.println("added: " + currInput);
                        userInputs[index] = new Task(currInput);
                        index++;
                    }
                }
            }
                currInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
