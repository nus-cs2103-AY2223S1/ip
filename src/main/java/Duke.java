import java.util.*;

public class Duke {
    public static void main(String[] args) {
      System.out.println("hello");
      System.out.println("can i help you?");

      Scanner sc = new Scanner(System.in);
      String s = sc.nextLine();
      while (!s.equals("yes")) {
        System.out.println(s);
        s = sc.nextLine();
      }

      System.out.println("peace");
    }
}
