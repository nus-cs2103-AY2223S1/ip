import java.util.*;
public class Duke {
    final static String HORIZON = "____________________________________________________________\n";
    static String withFormat(String str) {
        return HORIZON + str + HORIZON;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        String intro = withFormat("Pika Pikachu! (I am Pikachu!)\nPi-ka-chu?(Do you need any help?)\n");
        String byebye = withFormat("Pi?Pi-ka...(Alright...I will leave...)\n");
        System.out.println(intro);

        boolean done = false;

        //loop starts
        while (!done) {
            String input = sc.nextLine();

            if (input.equals("bye")) { //bye
                done = true;
                System.out.println(byebye);
            } else if (input.equals("list")) { //list all items
                int index = 1;
                StringBuilder output = new StringBuilder();
                for (String task: tasks) {
                    output.append(index).append(". ").append(task).append('\n');
                    index ++;
                }
                if (output.length() == 0) {
                    System.out.println(withFormat("PikaPika(Not available)\n"));
                } else {
                    System.out.println(withFormat(String.valueOf(output)));
                }
            }
            else { //add as tasks
                tasks.add(input);
                System.out.println(withFormat("Pikapi(added): " + input + '\n'));
            }
        }
    }
}
