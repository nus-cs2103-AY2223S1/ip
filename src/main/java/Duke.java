import java.util.Scanner;

public class Duke {
    private static String horizontalBorder = "_________________________________\n";
    private String[] toDoList = new String[100];
    private int count = 0;

    public static String welcomeMessage(){
        return horizontalBorder + "Hello! I'm Duke \nWhat can I do for you? \n" + horizontalBorder;
    }

    public String list(){
        String list = "";
        int localCount = 1;
        while (toDoList[localCount - 1] != null){
            list += localCount + ". " + toDoList[localCount - 1] + "\n";
            localCount += 1;
        }
        return horizontalBorder + list + horizontalBorder;
    }

    public String level2(){
        System.out.println(welcomeMessage());
        while(true) {
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            if (s.toLowerCase().equals("bye")) {
                return horizontalBorder + "Bye. Hope to see you again soon! \n" + horizontalBorder;
            } else if (s.equals("list")) {
                System.out.println(list());
            } else {
                toDoList[count] = s;
                count += 1;
                System.out.println(horizontalBorder + "added: " + s + "\n" + horizontalBorder);
            }
        }
    }

    public static void main(String[] args) {
        Duke sampleDuke = new Duke();
        System.out.println(sampleDuke.level2());
    }
}
