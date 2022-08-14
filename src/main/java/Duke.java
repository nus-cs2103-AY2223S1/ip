public class Duke {
    public static void main(String[] args) {
        String logo = "     _   _    ______     _____ ____\n"
                + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
                + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
                + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
                + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";
        String line = "____________________________________________________________";
        System.out.println(logo);
        System.out.println(line);
        say("Hello. I'm Jarvis");
        say("What can I do for you?");
        System.out.println(line);
    }

    public static void say(String message) {
        System.out.println(" " + message);
    }
}
