# Henry, the helpful chatbot

> The busy man is least busy with living. -
> Seneca ([source](https://www.goodreads.com/quotes/9840711-there-is-nothing-the-busy-man-is-less-busied-with#:~:text=%E2%80%9CThere%20is%20nothing%20the%20busy%20man%20is%20less%20busied%20with,that%20is%20harder%20to%20learn.%E2%80%9D))

## **Henry** is a chatbot that curates a task list for you based on text commands.

Currently, **Henry** supports the following commands:

- **Echo:** repeats the user's input to the console
- **List:** displays the task list in the console
- **Todo:** adds a "to-do" type task to the task list
- **Deadline:** adds a "deadline" type task to the task list
- **Event:** adds an "event" type task to the task list
- **Mark:** marks a task as complete
- **Unmark:** marks a task as incomplete
- **Delete:** deletes a task from the task list
- **Find:** finds any matching tasks given a key word
- **Interact:** rudimentary interaction feature that supports learning and memory

**Henry** is able to store a task list to the user's desktop. When **Henry** starts up, the following occurs:

1. The user's desktop is checked for an existing storage file. If no file exists, a new file is created.
   (Note: **Henry** does this by using a reference to a user's desktop that will work for ANY **Windows** user)

```java
private static final String home = System.getProperty("user.home");
private static final Path FILE_PATH = java.nio.file.Paths.get(home,"Desktop","henry.txt");
```

2. Tasks are parsed from the file to a list (or an empty list if the file is newly created).
3. The UI is initialised.
4. Start-up complete! 😃

To run **Henry**, download the release from **releases**, then open command prompt. Input the following:

```
cd [the path where henry.jar is located]
java -jar henry.jar
```

Note that the release is not updated to handle the "Find" command yet!

This description contains the following formatting guidelines:

- [x] a heading
- [x] a bullet list
- [x] a numbered list
- [x] a fenced code block (with syntax highlighting)
- [x] a task list
- [x] an emoji
- [x] a blockquote
- [x] a hyper link
- [x] inline code
- [x] some text formatting: bold, italic, strikethrough etc.

## **Acknowledgements**

### **Third-party libraries**

1) https://mvnrepository.com/artifact/org.goldrenard/ab (ALICE chatbot implementation)
2) https://mvnrepository.com/artifact/net.reduls.sanmoku/sanmoku (Dependency for ALICE)
3) https://mvnrepository.com/artifact/net.reduls.sanmoku/sanmoku-feature-ex (Dependency for ALICE)
4) https://mvnrepository.com/artifact/org.slf4j/slf4j-api (Dependency for ALICE)
5) https://mvnrepository.com/artifact/org.slf4j/log4j-over-slf4j (Dependency for ALICE)
