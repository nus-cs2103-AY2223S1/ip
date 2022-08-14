import java.util.Scanner;
public class Duke {
    private Task[] tasks;
    private int currentTaskCount = 0;

    private final String horizontalLine = "------------------------------------------------------------\n";
    Duke() {
        tasks = new Task[100];
    }

    private void printStoredInputs() {
        if (currentTaskCount > 0) {
            System.out.print(horizontalLine);
            System.out.println("Boss ah, this one your tasks:");
            for (int i = 0; i < currentTaskCount; i++) {
                System.out.println(i + 1 + ". " + tasks[i]);
            }
            System.out.print(horizontalLine);
        }

    }

    private void addTask(Task input) {
        tasks[currentTaskCount++] = input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke uncleCheong = new Duke();
        System.out.printf("%sEh hello, my name is Uncle Cheong. \n" +
                "What you want?\n%s\n", uncleCheong.horizontalLine, uncleCheong.horizontalLine);
        String input = "";
        while (!input.equals("bye")) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                uncleCheong.printStoredInputs();
            } else if (input.length() >= 6 && input.substring(0, 4).equals("mark")) {
                String taskIndexString = input.substring(4).trim();
                String messageToPrint = "";
                try {
                    int taskIndex = Integer.parseInt(taskIndexString);
                    uncleCheong.tasks[taskIndex - 1].changeIsDone(true);
                    messageToPrint = "Swee lah! Your task done liao: \n" + uncleCheong.tasks[taskIndex - 1];
                } catch (IllegalStateException e) {
                    messageToPrint = "Eh, you done that task alr lah";
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    messageToPrint = "Eh, you got that task number meh?";
                } catch (NumberFormatException e) {
                    messageToPrint = "Eh, you enter your task number correctly anot?";
                } finally {
                    System.out.println(messageToPrint);
                }
            } else if (input.length() >= 8 && input.substring(0, 6).equals("unmark")) {
                String taskIndexString = input.substring(6).trim();
                String messageToPrint = "";
                try {
                    int taskIndex = Integer.parseInt(taskIndexString);
                    uncleCheong.tasks[taskIndex - 1].changeIsDone(false);
                    messageToPrint = "Eh? Not done yet? Okay I change liao: \n" + uncleCheong.tasks[taskIndex - 1];
                } catch (IllegalStateException e) {
                    messageToPrint = "Eh, your task alr not done lah";
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    messageToPrint = "Eh, you got that task number meh?";
                } catch (NumberFormatException e) {
                    messageToPrint = "Eh, you enter your task number correctly anot?";
                } finally {
                    System.out.println(messageToPrint);
                }
            } else {
                System.out.printf("%sadded: %s\n%s", uncleCheong.horizontalLine, input, uncleCheong.horizontalLine);
                Task newTask = new Task(input);
                uncleCheong.addTask(newTask);
            }
        }
        System.out.printf("%sEh you leaving me so soon?\n%s", uncleCheong.horizontalLine, uncleCheong.horizontalLine);
    }
}
