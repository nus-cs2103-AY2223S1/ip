import java.util.Scanner;

public class Chacha {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chacha\n" + "What can I do for you?");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        while (!s.equals("bye")) {
            System.out.println(s);     
            s = input.nextLine();      
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
