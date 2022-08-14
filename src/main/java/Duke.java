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

    private void startChatBot() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("%sEh hello, my name is Uncle Cheong. \n" +
                "What you want?\n%s\n", this.horizontalLine, this.horizontalLine);
        this.getInputs(sc);
        System.out.printf("%sEh you leaving me so soon?\n%s", this.horizontalLine, this.horizontalLine);
    }

    private void getInputs(Scanner sc) {
        String input = "";
        boolean terminated = false;
        while (!terminated) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                this.printStoredInputs();
            } else if (input.length() >= 6 && input.substring(0, 4).equals("mark")) {
                String taskIndexString = input.substring(4).trim();
                String messageToPrint = "";
                try {
                    int taskIndex = Integer.parseInt(taskIndexString);
                    this.tasks[taskIndex - 1].changeIsDone(true);
                    messageToPrint = "Swee lah! Your task done liao: \n" + this.tasks[taskIndex - 1];
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
                    this.tasks[taskIndex - 1].changeIsDone(false);
                    messageToPrint = "Eh? Not done yet? Okay I change liao: \n" + this.tasks[taskIndex - 1];
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
                System.out.printf("%sadded: %s\n%s", this.horizontalLine, input, this.horizontalLine);
                Task newTask = new Task(input);
                this.addTask(newTask);
            }
        }
    }
    public static void main(String[] args) {
        Duke uncleCheong = new Duke();
        uncleCheong.startChatBot();
    }
}
