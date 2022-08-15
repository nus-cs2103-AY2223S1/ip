import java.util.*;

public class Duke {
    public static void main(String[] args) {
      System.out.println("hello");
      System.out.println("can i help you?");
      
      int count = 0;
      String[] strArray = new String[101];

      Scanner sc = new Scanner(System.in);
      String s = sc.nextLine();
      while (!s.equals("bye")) {
        if (s.equals("list")) {
          for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ". " + strArray[i]);
          }
        } else {
          strArray[count] = s;
          count++;
          System.out.println("added: " + s);
        }
        s = sc.nextLine();
      }

      System.out.println("peace");
    }
}
