
package duke;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser {


    public String startParse() {
        System.out.println("Hello! I'm Duke What can I do for you?");
        ArrayList<Task> array = new ArrayList<>(100);
        int counter = 0;
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        DukeOperations ops = new DukeOperations();
        TaskList tl = new TaskList();
        Storage storage = new Storage();

        while (!flag) {
            String output = in.nextLine();
            String arr[] = output.split(" ", 2);
            String firstword = arr[0];

            if (output.equals("bye")) {
                ops.exit();
                flag = true;

            } else if (output.equals("list")) {
                ops.displayList(array, counter);

            } else if (firstword.equals("todo")) {
                if (!storage.fileExists()) {
                    System.out.println("File doesnt exist yet");
                }
                try {
                    tl.todo(array, arr, counter);
                    counter++;
                    Storage.clearFile();
                    Storage.writeToFile(array, counter);

                } catch (DukeException e1) {
                    System.out.println(e1.toString());
                }

            } else if (firstword.equals("deadline")) {
                if (!storage.fileExists()) {
                    System.out.println("File doesnt exist yet");
                }


                String arr2[] = arr[1].split("/by ", 2);
                String arr3[] = arr2[1].split(" ", 2);
                tl.deadline(array, arr2[0], arr3[0], arr3[1], counter);
                counter++;
                Storage.clearFile();
                Storage.writeToFile(array, counter);

            } else if (firstword.equals("event")) {
                if (!storage.fileExists()) {
                    System.out.println("File doesnt exist yet");
                }

                String arr2[] = arr[1].split("/at ", 2);
                String arr3[] = arr2[1].split(" ", 2);
                tl.event(array, arr2[0], arr3[0], arr3[1], counter);
                counter++;
                Storage.clearFile();
                Storage.writeToFile(array, counter);

            } else if (firstword.equals("mark")) {
                int num = Integer.parseInt(arr[1]);
                ops.mark(array, num);
            } else if (firstword.equals("unmark")) {
                int num = Integer.parseInt(arr[1]);
                ops.mark(array, num);
            } else if (firstword.equals("delete")) {
                System.out.println("_________________________________________________________________________");
                int num = Integer.parseInt(arr[1]);
                tl.delete(array, num, counter);
                counter--;
                System.out.println("_________________________________________________________________________");
                Storage.clearFile();
                Storage.writeToFile(array, counter);

            } else if(firstword.equals("find")) {
                System.out.println("_________________________________________________________________________");
                String str = arr[1];
                ops.findWord(str,array,counter);
                System.out.println("_________________________________________________________________________");


            } else {
                try {
                    ops.randomword(output);
                } catch (DukeException e2) {
                    System.out.println("_________________________________________________________________________");
                    System.out.println(e2.toString());
                    System.out.println("_________________________________________________________________________");
                }
            }
        }

        return "Hello! I'm Duke What can I do for you?";
    }
}
