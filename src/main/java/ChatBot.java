import java.util.ArrayList;

class ChatBot {

    private String name;
    private final String line = "------------------------------" +
            "----------------------------------";
    private ArrayList<String> tasks;

    ChatBot(String name) {

        this.name = name;
        this.tasks = new ArrayList<String>();

    }

    public void greet() {

        System.out.println(line + "\n\t Hello I'm " + name + "!!\n" +
                "What do you wanna chat about today?\n" + line);
    }

    public void addTask(String input) {

        this.tasks.add(input);
        System.out.println(line + "\n\t added: " + input + "\n" + line);
    }

    public void printTasks() {

        System.out.println(line);

        for(int i = 0; i < this.tasks.size(); i++) {

            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
        }
        System.out.println(line);
    }

    public void echo(String input) {

        System.out.println(line + "\n\tYou just said " + input + "\n" + line);
    }

    public void bye() {

        System.out.println(line + "\n\t Bye. Looking forward to chating " +
                "with you soon again!\n" + line);
    }

}
