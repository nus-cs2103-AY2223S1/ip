import java.util.ArrayList;
import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        System.out.println("Hello! Got any grapes?");
        Scanner scanner = new Scanner(System.in);
        String word = "";
        ArrayList<Task> list = new ArrayList<>();
        while (!word.equals("bye")) {
            word = scanner.nextLine();
            if (word.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            } else if(word.contains("unmark")) {
                String[] split= word.split("\\s+");
                Task currentTask = list.get(Integer.parseInt(split[1]) - 1);
                currentTask.unCompleteTask();
                System.out.println("Quack, unmarked! " + currentTask);
            } else if(word.contains("mark")) {
                String[] split = word.split("\\s+");
                Task currentTask = list.get(Integer.parseInt(split[1]) - 1);
                currentTask.completeTask();
                System.out.println("Quack, marked! " + currentTask);
            }  else {
                System.out.println("added: " + word);
                list.add(new Task(word));
            }
        }
        System.out.println("Quack!");
    }
}
