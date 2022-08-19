import java.util.Scanner;

public class Duke {
    private static final String horizontalBorder = "_________________________________\n";
    private Tasklist tasklist;

    private Duke(){
        this.tasklist = new Tasklist(10);
    }

    private Duke(Tasklist tasklist){
        this.tasklist = tasklist;
    }

    public static String welcomeMessage(){
        return horizontalBorder + "Hello! I'm Duke \nWhat can I do for you? \n" + horizontalBorder;
    }


    public String run(){
        System.out.println(welcomeMessage());
        while(true) {
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            if (s.toLowerCase().equals("bye")) {
                return horizontalBorder + "Bye. Hope to see you again soon! \n" + horizontalBorder;
            } else if (s.toLowerCase().equals("list")) {
                System.out.println(horizontalBorder + tasklist + horizontalBorder);
            } else {
                tasklist.add(new Task(s));
                System.out.println(horizontalBorder + "added: " + s + "\n" + horizontalBorder);
            }
        }
    }

    public static void main(String[] args) {
        Duke sampleDuke = new Duke(new Tasklist(100));
        System.out.println(sampleDuke.run());
    }
}
