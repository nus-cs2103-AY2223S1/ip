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
        String byebye = "Pi-ka...";
        System.out.println(intro);

        boolean done = false;

        //loop starts
        while (!done) {
            String input = sc.nextLine();
            String tempStr = "";

            if (input.equals("bye")) { //bye
                done = true;
                tempStr = byebye;
            } else if (input.equals("list")) { //list all items
                StringBuilder output = new StringBuilder();
                output.append("PikaPika\n");
                for (Task task: tasks) {
                    output.append(tasks.indexOf(task)+1).append('.').append(task).append('\n');
                }
                output.deleteCharAt(output.length() - 1);
                tempStr = String.valueOf(output);
            } else if (input.startsWith("mark ") && isNumeric(input.substring(5)) && Integer.parseInt(input.substring(5)) <= tasks.size() && Integer.parseInt(input.substring(5)) >= 1) {
                int temp = Integer.parseInt(input.substring(5));
                Task task = tasks.get(temp - 1);
                task.setDone(true);
                tempStr="Pi-ka(Done): " + '[' + task;
            } else if (input.startsWith("unmark ") && isNumeric(input.substring(7)) && Integer.parseInt(input.substring(7)) <= tasks.size() && Integer.parseInt(input.substring(7)) >= 1) {
                int temp = Integer.parseInt(input.substring(7));
                Task task = tasks.get(temp - 1);
                task.setDone(false);
                tempStr="Pipi-ka(Undone): " + task;
            } else if (input.startsWith("todo ")){ //add as tasks
                Todo newTODO = new Todo(input.substring(5));
                tasks.add(newTODO);
                tempStr = "Pikapi(added): " + newTODO + '\n';
                tempStr += "Pikaaaaa: " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
            } else if (input.startsWith("deadline ") && input.contains(" /by ")) {
                String temp1 = input.split(" ",2)[1];
                String[] temp2 = temp1.split(" /by ",2);
                Deadline newDDL = new Deadline(temp2[0],temp2[1]);
                tasks.add(newDDL);
                tempStr = "Pikapi(added): " + newDDL + '\n';
                tempStr += "Pikaaaaa: " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
            } else if (input.startsWith("event ") && input.contains(" /at ")){
                String temp1 = input.split(" ",2)[1];
                String[] temp2 = temp1.split(" /at ",2);
                Event newEvent = new Event(temp2[0],temp2[1]);
                tasks.add(newEvent);
                tempStr = "Pikapi(added): " + newEvent + '\n';
                tempStr += "Pikaaaaa: " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
            } else {
                tempStr = "Pi?";
            }
            System.out.println(withFormat(tempStr));
        }
    }
}
