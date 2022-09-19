# DukeAce User Guide


Meet DukeAce, your very own task manager

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/AshiqurRah/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)


5. Refer to the [Features](#features) below for details of each command.


## Features 

### Keep Track of Todos, Events and Deadlines

DukeAce, your task manager is able to record your tasks. 
DukeAce is also able to categorise your tasks into 3 types

- Todos
- Events
- Deadlines

Deadlines are able to record the date of the deadlines as well.

### Use actions and work with your tasks  

Input actions to optimise your experience with DukeAce

|    action     | Description                                      |
|:-------------:|:-------------------------------------------------|
|    `mark`     | To check your task as done                       |
|   `unmark`    | To uncheck your task as undone                   |
|   `delete`    | To remove task from the list                     | 
|    `find`     | To retrieve a list of tasks that has the keyword | 



## Usage

---
### `hi`, `hello`  - DukeAce responds back hi

DukeAce will show greeting message when received a "hi" input.

Example of usage: 

`hi` or `hello`

Expected outcome:

```
Hello! I'm Darwin :)
What can I do for you?
```

---
### `bye` - DukeAce says back bye

DukeAce will show sending off message when received a "bye" input.

Example of usage:

`bye`

Expected outcome:

```
Aww going so soon :(( 
Hope to see you again soon!
```


---
### `todo` - DukeAce create todo Task

DukeAce will create todo task.

Example of usage:

`todo Homework & Project`

Expected outcome:


```
Oki, nicee. I've added this task:
[T][ ] Homework & Project
Now you have 3 tasks in the list.
```

---
### `event` - To create event Task

DukeAce will create event task.

Example of usage:

`event Party /at my place 3pm`

Expected outcome:

```
Oki, niceee. I've added this task:
[E][ ] Party (at: my place 3pm)
Now you have 4 tasks in the list.
```

---
### `deadline` - To create deadline Task

DukeAce will create deadline task that has a date for deadline.

Example of usage:

`deadline Project Submission /by 2021-07-24`

Input has to be in yyyy-MM-dd as the format for deadline date.

Expected outcome:

Outcome will be in MM dd yyyy format

```
Oki, niceee. I've added this task:
[D][ ] Deadline (by: Jul 24 2021)
Now you have 5 tasks in the list.
```

---
### `mark` - To mark a Task as complete

DukeAce will mark the task as complete.

Example of usage:

`mark 1`

Expected outcome:

Task with order number 1 will be marked.

```
Nice! I've marked this task as done:
[T][X] Project to be completed
```

---
### `unmark` - To mark a Task as incomplete

DukeAce will mark the task as complete.

Example of usage:

`unmark 1`

Expected outcome:

Task with order number 1 will be marked.

```
Aww, I've marked this task as not done yet:
[T][ ] Project to be completed
```

---
### `delete` - To remove a Task

DukeAce will remove task with respect to the index.

Example of usage:

`delete 1`

Expected outcome:

Task with order number 1 will be removed.

```
One less task. I've removed this task
[E][ ] Party (at: my place 3pm)                
Now you have %d tasks in the list.You got this!
```

---
### `find` - To find a List of Tasks with the keyword

DukeAce will find tasks with respect to the keyword and returns a list.

Example of usage:

`find homework`

Expected outcome:

Task with order number 1 will be removed.

```
Found these tasks in your list:
1.[D][ ] Math homework (by: Jul 24 2021)                
2.[T][X] Chem homework
```

---
### `... all` - Mass Command

DukeAce will apply the prefix action on all tasks.

Actions that are compatible with the mass command
* mark
* unmark
* delete

Example of usage:

`mark all`

`unmark all`

`delete all`

Expected outcome:

```
You are really amazing!! I've marked all 4 task(s) as done.
```
```
Awww, I've marked all 4 task(s) as not done yet.
```
```
The tasks were boring anyway, I've removed all 4 task(s)
Now you have 0 task in the list.
```


###Command Summary

|  Command   | Format                                       |                   Example                    |
|:----------:|:---------------------------------------------|:--------------------------------------------:|
|   `mark`   | `mark` \[order index]                        |                   `mark` 1                   |
|  `unmark`  | `unmark` \[order index]                      |                  `unmark` 2                  |
|  `delete`  | `delete` \[order index]                      |                  `delete` 3                  |
|   `find`   | `find` \[keyword]                            |                `find` project                |
|   `todo`   | `todo` \[description]                        |               `todo` homework                |
|  `event`   | `event` \[description /at more description ] |        `event` Party /at my place 3pm        |
| `deadline` | `deadline`\[description /by yyyy-MM-dd]      | `deadline` Project Submission /by 2021-07-24 |
| `... all`  | `delete all`,`mark all`, `unmark all`        |    `delete all`,`mark all`, `unmark all`     |
