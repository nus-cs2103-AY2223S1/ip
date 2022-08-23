public class Argument {
    private final String name, body;

    Argument(String name) {
        this.name = name;
        this.body = null;
    }

    Argument(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return this.name;
    }

    public String getBody() {
        return this.body;
    }

    @Override
    public String toString() {
        return String.format("Argument %s: %s", this.name, this.body);
    }
}
