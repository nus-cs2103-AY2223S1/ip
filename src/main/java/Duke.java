import java.util.Scanner;

public class Duke {
    // mtd to store arraylist of 100
    public static void main(String[] args) {

        System.out.println("Hello! I'm Milk");
        System.out.println("what can I do for you?");

        Scanner sc = new Scanner(System.in);
        String userReply = sc.nextLine();
        while (!userReply.equals("bye")) {
            System.out.println(userReply);
            userReply = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
