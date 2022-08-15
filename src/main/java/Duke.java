import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = "*\\(^o^)/*";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("Bonjour~~ I'm Byu! How can I help you?");
        String response = sc.nextLine();
        while (!response.equals("bye")) {
            System.out.println(response + "\n");
            response = sc.nextLine();
        }
        System.out.println("Awww see you soon!");
        sc.close();
    }

}
