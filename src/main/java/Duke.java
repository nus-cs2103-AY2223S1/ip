import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static boolean isNumeric(String string) {
        int intValue;


        if(string == null || string.equals("")) {

            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {

        }
        return false;
    }

    public static void main(String[] args) {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢀⣴⣿⡿⠟⠋⠉⠀⠀⠀⠀⠀⠉⠛⠻⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣰⣿⠟⠉⠀⠀⠀⠀⠀⠀⢀⣠⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀\n" +
                "⠀⠀⣰⣿⠋⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀\n" +
                "⠀⢰⣿⠇⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀\n" +
                "⠀⣿⡿⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀\n" +
                "⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀\n" +
                "⠀⣿⣷⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀\n" +
                "⠀⢸⣿⡄⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀\n" +
                "⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀\n" +
                "⠀⠀⠀⠹⣿⣦⡀⠀⠀⠀⠀⠀⠀⠈⠛⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⠻⣿⣷⣤⣄⣀⠀⠀⠀⠀⠀⣀⣠⣴⣾⣿⣿⣿⡿⠟⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠈⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠛⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        String lineBreak = "°.✩┈┈∘*┈\uD83C\uDF19┈*∘┈┈✩.°";
        System.out.println(logo + "\n" + "Hello I am LUNA!\n" + "How can I be of help?\n" + lineBreak);

        ArrayList<Task> arrayList =  new ArrayList<>(100);



        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();


            String farewell = lineBreak + "\n" + "゜✧*̣̩☽⋆゜LUNA bids farewell ゜✧*̣̩☽⋆゜\n\n May the Moon\uD83C\uDF19 shine bright and illuminate your night.";


            if (input.equalsIgnoreCase("bye")) {
                System.out.println(farewell);
                break;

            } else if (input.equalsIgnoreCase("list")) {
                int counter = 1;
                System.out.println(lineBreak);
                for (Task s : arrayList) {
                    System.out.println(counter + ". " + s.toString());
                    counter++;
                }
            } else if (input.contains("mark")) {
                String[] splitStr = input.split("\\s+");
                String str = splitStr[1];
                if (splitStr.length > 2 || !isNumeric(str) ) {
                    Task task = new Task(input);
                    arrayList.add(task);
                    System.out.println(lineBreak + "\n" + "LUNA has added the task: " + input + "\n" + lineBreak);
                } else {

                    int index = Integer.valueOf(str) - 1;

                    if (input.contains("unmark")) {
                        Task task = arrayList.get(index);
                        task.unmark();
                        String taskName = task.toString();

                        arrayList.set(index, task);

                        System.out.println("LUNA thought you were already done with this?\n" + taskName + "\n" + lineBreak);
                    } else {


                        Task task = arrayList.get(index);
                        task.mark();
                        String string = task.toString();

                        arrayList.set(index, task);

                        System.out.println("LUNA waited many moons for you to finish this one\n" + string + "\n" + lineBreak);
                    }
                }

            } else {
                Task task = new Task(input);
                arrayList.add(task);
                System.out.println(lineBreak + "\n" + "LUNA has added the task: " + input + "\n" + lineBreak);

            }
        }
    }
}
