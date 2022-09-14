# TedBot
> "Hello! I'm TedBot ヾ(≧▽≦*)o"

TedBot is a _simple_ chatbot made with Java. It:
* is text-based,
* stores all your tasks,
* is ~~fast~~ SUPER FAST!

To get started, simply:
1. Download the JAR file from [here](https://github.com/Berted/ip/releases) 👈
1. Run TedBot in your preferred terminal 👩‍💻
1. Add your tasks ✍
1. Let it store and handle your tasks for you 👍

Most importantly, it's **FREE**!

Features
- [x] Manages simple tasks
- [x] Manages tasks with deadlines
- [x] Manages tasks with a start and end date
- [x] A GUI
- [ ] Reminders for deadlines and events (coming soon!)

For a more comprehensive explanation, feel free to visit the [project's webpage](https://berted.github.io/ip)!

---

If you're a Java programmer, you can use this codebase to help you practice Java. 
For example, here's an excerpt of our code, taken from the `Ui` class:
```java
private void outputString(String out, String header) {
    String[] splitOut = out.split("\n");
    System.out.print(header + " ");
    System.out.println(splitOut[0]);
    for (int i = 1; i < splitOut.length; i++) {
        for (int j = 0; j < header.length() + 1; j++) {
            System.out.print(" ");
        }
        System.out.println(splitOut[i]);
    }
}
```

## Background

TedBot is based on the [SE-Education's Duke](https://github.com/se-edu/duke) greenfield
Java project template. This project is an individual project created for the purposes 
of NUS CS2103T Software Engineering module.

