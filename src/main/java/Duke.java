import java.util.*;
public class Duke {
    final static String HORIZON = "____________________________________________________________\n";
    static String withFormat(String str) {
        return HORIZON + str + '\n' + HORIZON;
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        String intro = withFormat("Pika Pikachu! (I am Pikachu!)\nPi-ka-chu?(Do you need any help?)");
        String byebye = withFormat("Pi?Pi-ka...(Alright...I will leave...)");
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
                output.append("PikaPika\n");
                for (Task task: tasks) {
                    output.append(index).append(".").append('[').append(task.getStatusIcon()).append("] ").append(task.description).append('\n');
                    index ++;
                }
                output.deleteCharAt(output.length() - 1);
                System.out.println(withFormat(String.valueOf(output)));
            } else if (input.startsWith("mark ") && isNumeric(input.substring(5)) && Integer.parseInt(input.substring(5)) <= tasks.size() && Integer.parseInt(input.substring(5)) >= 1) {
                int temp = Integer.parseInt(input.substring(5));
                Task task = tasks.get(temp - 1);
                task.setDone(true);
                System.out.println(withFormat("Pi-ka(Done): " + '[' + task.getStatusIcon() + "] " + task.description));
            } else if (input.startsWith("unmark ") && isNumeric(input.substring(7)) && Integer.parseInt(input.substring(7)) <= tasks.size() && Integer.parseInt(input.substring(7)) >= 1) {
                int temp = Integer.parseInt(input.substring(7));
                Task task = tasks.get(temp - 1);
                task.setDone(false);
                System.out.println(withFormat("Pipi-ka(Undone): " + '[' + task.getStatusIcon() + "] " + task.description));
            } else { //add as tasks
                tasks.add(new Task(input));
                System.out.println(withFormat("Pikapi(added): " + input));
            }
        }
    }
}
