import java.util.*;

public class Doke {

    private static void listOut(ArrayList<Task> arr) {
        if (arr.isEmpty()) {
            System.out.println("_________________________");
            System.out.println("You have no task!");
            System.out.println("_________________________");
            return;
        }

        int len = arr.size();
        int i=0;
        System.out.println("_________________________");
        while (i < len) {
            System.out.println((i + 1) + "." + arr.get(i).toString());
            i++;
        }
        System.out.println("_________________________");
    }

    private static boolean isInt(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }

    }

    private static int toInt(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return x; //String is an Integer
        } catch (NumberFormatException e) {
            return -1; //String is not an Integer
        }

    }

    private static void addTask(String[] arr, String action,
                                          ArrayList<Task> arrayList) {

        if (arr.length == 1) {
            if (action.equals("todo")) {
                try {
                    throw new DokeException("todo");
                } catch (DokeException d) {
                    System.out.println(d.toString());
                }
            } else if (action.equals("deadline")) {
                try {
                    throw new DokeException("deadline");
                } catch (DokeException d) {
                    System.out.println(d.toString());
                }
            }else {
                try {
                    throw new DokeException("todo");
                } catch (DokeException d) {
                    System.out.println(d.toString());
                }
            }
            return;
        }

        String word = "";
        String time="";
        int i = 1;
        int length = arr.length;

        while (i < length && ((!arr[i].equals("/at") && action.equals("event"))
                || (!arr[i].equals("/by") && action.equals("deadline"))
                || action.equals("todo"))) {
            word = word + " " + arr[i];
            i++;
        }

        if (i != length) {
            time = arr[++i];
            i++;
            while (i< length) {
                time += " " + arr[i];
                i++;
            }
        }

        if (action.equals("todo")) {
            Task addition =  new ToDo(word);
            arrayList.add(addition);
        } else if (action.equals("deadline")) {
            Task addition =  new Deadline(word, time);
            arrayList.add(addition);
        } else {
            Task addition =  new Events(word, time);
            Task add =  new ToDo(word);
            arrayList.add(addition);
        }

        System.out.println("_________________________");
        System.out.println("added: " + arrayList.get(arrayList.size() - 1).toString());
        System.out.println("Nice, now you have " + arrayList.size() + " tasks!!");
        System.out.println("_________________________" +"\n");
    }

    public static void main(String[] args) {

        ArrayList<Task> arrayList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("_________________________");
        System.out.println("    Hi, my name is Doke");
        System.out.println("    What can I do for you?");
        System.out.println("    Enter a String!!");
        System.out.println("_________________________" + "\n");

        String str= sc.nextLine();

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                listOut(arrayList);
                str = sc.nextLine();
                continue;
            }

            String[] temp = str.split(" ");
            String action = temp[0];

            if (temp.length == 2 && isInt(temp[1])) {
                int num = toInt(temp[1]);
                if(action.equals("mark")) {

                    Task current = arrayList.get(num - 1);
                    try {
                        current.markDone();
                        System.out.println("_________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");
                    } catch (DokeException a) {
                        System.out.println("_________________________");
                        System.out.println("This task is already marked done:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");
                    }
                } else if(action.equals("unmark")) {

                    Task current = arrayList.get(num - 1);
                    try {
                        current.markNotDone();
                        System.out.println("_________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");
                    } catch (DokeException a) {
                        System.out.println("_________________________");
                        System.out.println("This task is already marked not done:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");
                    }
                } else if(action.equals("delete")) {

                    Task current = arrayList.get(num - 1);
                    arrayList.remove(num-1);
                    System.out.println("_________________________");
                    System.out.println("This task has been removed:");
                    System.out.println(current.toString());
                    System.out.println("Nice, now you have " + arrayList.size() + " tasks!!");
                    System.out.println("_________________________" + "\n");
                }
                str = sc.nextLine();
                continue;
            }

            if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
                addTask(temp, action, arrayList);
            } else {
                try {
                    throw new DokeException();
                } catch(DokeException d){
                    System.out.println(d.toString());
                }
                str = sc.nextLine();
                continue;
            }

            str = sc.nextLine();
        }

        System.out.println("_________________________");
        System.out.println("    Bye bye!");
        System.out.println("_________________________");

    }
}