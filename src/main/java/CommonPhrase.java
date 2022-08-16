enum CommonPhrase {
    HELLO("Hello! I'm Duke\n" + "I'm ready to serve you!"),
    GOODBYE("Goodbye, Hope to see you soon!"),
    BOT_DIVIDER("~~~~~-----DUKE-----~~~~~\n"),
    USER_DIVIDER("~~~~~-----YOU-----~~~~~~\n");

    private final String text;

    CommonPhrase(String text) {
        this.text = text;
    }

    public String getPhrase() {
        return this.text;
    }
}
