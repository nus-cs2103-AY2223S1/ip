# User Guide to Duke

## Overview


Duke is a chatbot which allows you to manage tasks, namely:
- Note down new todos, deadlines and events
- Mark off completed tasks one by one
- Get quick reminders for the next week ahead

### Quick Start Checklist

- [ ] Ensure you have Java 11 or above installed on your Computer


- [ ] Download the latest Jar File of Duke, available here


- [ ] Copy the file to the folder you want to use as the home folder for Duke


- [ ] Double click Duke to run

![Welcome to Duke](https://tan-jin-waye.github.io/ip/Ui.png)

- [ ] Start with a todo. Try typing in `todo learn duke` and hit **Send**


- [ ] View your newly added task with the `list` command

### **For more details and features of Duke, refer to Features**

## Features

### Task Types

There are three different types of tasks, namely **Todo**, **Deadline** and **Event**.

Examples of tasks look like this:

`[T][ ] Return book`

`[D][X] Finish assignment by: 17 Aug 2023`

`[E][ ] Attend lecture at: 9 Jan 2022`

From left to right:

- `[T]`, `[D]`, `[E]` denote a **Todo**, **Deadline**, or **Event** task respectively
- `[ ]`, `[X]` denote an **Active** or **Completed** task
- The text in the middle denotes the **Description** of the task
- **Deadline** and **Event** tasks additionally have a `by` or `at` date respectively

### Detailed Command List

| Command  |               Format                |                 Sample                 |                                                         Details                                                          |
|:--------:|:-----------------------------------:|:--------------------------------------:|:------------------------------------------------------------------------------------------------------------------------:|
|   todo   |        `todo <description>`         |           `todo Return Book`           |                                  Creates a **Todo** task with the supplied description.                                  |
| deadline | `deadline <description> /by <date>` | `deadline Assignment 1 /by 2022-01-02` | Creates a **Deadline** task with the supplied description and time. ***Date must be supplied in the format YYYY-MM_DD*** |
|  event   |  `event <description> /at <date>`   |     `event Lecture /at 2022-09-10`     |  Creates an **Event** task with the supplied description and date. ***Date must be supplied in the format YYYY-MM-DD***  |
|   list   |               `list`                |                 `list`                 |                  Lists your currently saved tasks in the order you added them, numbered from 1 onward.                   |
|   mark   |           `mark <index>`            |                `mark 1`                |            Toggles the completed status of the task at the given index of the list, as determined by `list`.             |
|  delete  |          `delete <index>`           |               `delete 1`               |                        Deletes the task at the given index of the list, as determined by `list`.                         |
|   find   |           `find <regex>`            |              `find book`               |                                 Lists the tasks whose contents match the provided regex.                                 |
|  remind  |              `remind`               |                `remind`                |                       Lists the tasks whose due dates are within a fortnight of the current date.                        |
|   bye    |                `bye`                |                 `bye`                  |                                                     Closes the app.                                                      |

