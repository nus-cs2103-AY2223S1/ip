import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Hello I'm Duke\nWhat can I do for you?\n");
      while (true){
        String str = sc.nextLine();
        if(str.equals("bye")){
          System.out.println("Bye. Hope to see you again soon!");
          break;
        } else {
          System.out.println(str);
        }

      }
    }
}
