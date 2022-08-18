class ChatBot {

    private String name;
    private final String line = "------------------------------" +
            "----------------------------------";

    ChatBot(String name) {

        this.name = name;

    }

    public void greet() {

        System.out.println(line + "\n\t Hello I'm " + name + "!!\n" +
                "What do you wanna chat about today?\n" + line);
    }

    public void echo(String input) {

        System.out.println(line + "\n\tYou just said " + input + "\n" + line);
    }

    public void bye() {

        System.out.println(line + "\n\t Bye. Looking forward to chating " +
                "with you soon again!\n" + line);
    }

}
