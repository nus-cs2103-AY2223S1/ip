import java.util.Scanner;

public class CleverNotBot {
    public static void main(String[] args) throws CleverNotBotException {

        UITextBox textBox = new UITextBox();
        TaskList tasks = new TaskList();
        Scanner sc = new Scanner(System.in);
        Handler hl = new Handler();

        hl.parseText("greet").run(tasks,textBox);
        while(sc.hasNext()){
            String ip = sc.nextLine();
            Function nxtStep = hl.parseText(ip);
            nxtStep.run(tasks,textBox);
            if(nxtStep.isExitingProgram()) { break; }
        }

    }

}
