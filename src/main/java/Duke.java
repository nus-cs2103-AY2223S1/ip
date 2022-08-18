import java.util.*;
public class Duke {
    final static String HORIZON = "____________________________________________________________\n";
    static List<Task> tasks = new ArrayList<>();
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

    static String validDelete(String input) {
        if (!isNumeric(input.substring(7))) {
            return "Pi-must be numbers behind-pi!";
        } else if (Integer.parseInt(input.substring(7)) > tasks.size() || Integer.parseInt(input.substring(7)) <= 0) {
            return "Pi-not within range-pi!";
        } else {
            int temp = Integer.parseInt(input.substring(7));
            Task task = tasks.get(temp - 1);
            tasks.remove(temp-1);
            return "Pi-ka(Removed): " + task + '\n' +"Pikaaaaa: " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
        }
    }

    static String validMark(String input) {
        if (!isNumeric(input.substring(5))) {
            return "Pi-must be numbers behind-pi!";
        } else if (Integer.parseInt(input.substring(5)) > tasks.size() || Integer.parseInt(input.substring(5)) <= 0) {
            return "Pi-not within range-pi!";
        } else {
            int temp = Integer.parseInt(input.substring(5));
            Task task = tasks.get(temp - 1);
            task.setDone(true);
            return "Pi-ka(Done): " + task;
        }
    }

    static String validUnmark(String input) {
        if (!isNumeric(input.substring(7))) {
            return "Pi-must be numbers behind-pi!";
        } else if (Integer.parseInt(input.substring(7)) > tasks.size() || Integer.parseInt(input.substring(7)) <= 0) {
            return "Pi-not within range-pi!";
        } else {
            int temp = Integer.parseInt(input.substring(7));
            Task task = tasks.get(temp - 1);
            task.setDone(false);
            return "Pipi-ka(Undone): " + task;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


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
            } else if (input.startsWith("mark ")) {
                tempStr = validMark(input);
            } else if (input.startsWith("unmark ")) {
                tempStr = validUnmark(input);
            } else if (input.startsWith("todo ")){ //add as tasks
                Todo newTODO = new Todo(input.substring(5));
                if (newTODO.description.equals("")) {
                    tempStr = "Pi-cannot be empty-pi";
                } else {
                    tasks.add(newTODO);
                    tempStr = "Pikapi(added): " + newTODO + '\n';
                    tempStr += "Pikaaaaa: " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
                }
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
            } else if (input.startsWith("delete ")) {
                tempStr = validDelete(input);
            } else {
                tempStr = "Pi?";
            }
            System.out.println(withFormat(tempStr));
        }
    }
}
