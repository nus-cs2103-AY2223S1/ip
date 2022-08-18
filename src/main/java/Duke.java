public class Duke {

    public static void selfIntro() {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";
        System.out.println("__________________________________________________");
        System.out.println(("Hola Amigo! My name is\n" + logo));
        System.out.println("__________________________________________________");
    }

    public static void main(String[] args) {
        selfIntro();
        ChatBox chat_feature = new ChatBox();

        chat_feature.Reply();
    }
}
