import java.util.*;
public class Duke {
    final static String HORIZON = "____________________________________________________________\n";
    static String withFormat(String str) {
        return HORIZON + str + HORIZON;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String intro = withFormat("Pika Pikachu! (I am Pikachu!)\nPi-ka-chu?(Do you need any help?)\n");
        String byebye = withFormat("Pi?Pi-ka...(Alright...I will leave...)\n");
        System.out.println(intro);

        Boolean done = false;

        //loop starts
        while (!done) {
            String input = sc.nextLine() + '\n';

            if (input.equals("bye\n")) {
                done = true;
                System.out.println(byebye);
            } else {
                System.out.println(withFormat(input));
            }
        }
    }
}
