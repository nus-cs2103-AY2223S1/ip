import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      List<String> list = new ArrayList<>();

      System.out.print("Hello I'm Duke\nWhat can I do for you?\n");
      while (true){
        String str = sc.nextLine();
        if(str.equals("bye")){
          System.out.println("Bye. Hope to see you again soon!");
          break;
        } else if (str.equals("list")) {
          for (int i = 1; i <= list.size(); i++){
            System.out.println(i + ". " + list.get(i-1));
          }
        } else {
          list.add(str);
          System.out.println("added: " + str);
        }
      }
    }
}
