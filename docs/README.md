# User Guide

This is the user guide for Duke.
Duke is a program designed for keyboard users. While it is designed with a graphical user interface, most of the user interactions happening on the CLI (Command Line Interface).

![image](https://user-images.githubusercontent.com/15359033/190663337-b55abb6a-11a2-4fa7-9e74-4935e45cce2d.png)

**Acknowledgements**

The appearance of the GUI would not have been possible without the help of these sources:

- The sliding animations on dialogue appearance and window resize are handled by a class that has been adapted from the following [LayoutAnimation](https://gist.github.com/jewelsea/5683558).

- The ability to resize chat bubbles based on text font, size and length is handled by a function adapted from a code piece from StackOverflow, [computeTextWidth()](https://stackoverflow.com/questions/12737829/javafx-textfield-resize-to-text-length)

I would like to thank [Seerlight](https://www.deviantart.com/seerlight) from DeviantArt for creating this beautiful wallpaper [Let's Go Home](https://www.deviantart.com/seerlight/art/Let-s-Go-Home-743542427). It is used as the background of the application and is free for personal use.

---
## Features

The following features are available in Duke:

- Tasks
  - Adding tasks
  - Deleting tasks
  - Listing tasks
  - Annotating tasks by completion status
  - Searching tasks by name
  
- Loans
  - Adding loans, or modifying exist amount loaned
  - Deleting loans
  - Listing loans
  
- Customisation
  - Setting the name that you would like Duke to call you
  
- Saving
  - Autosaving and serialising file to disk after every critical action
  - Serialising the file to disk at fixed time intervals
  
---
## Tasks

### Adding Tasks

There are three types of tasks:
1. Todo
1. Event
1. Deadline

To add a new task, specify `task add <task_type> ...`, where <task_type> is the type of task and the ellipses signify additional arguments. Regarding the format of additional arguments, please refer to the guide for each unique type of task.

#### Adding a Todo Task

One may add a Todo type task by specifying the task type, followed by the name of the task.

General Format: `tasks add todo <name>`

**Example**
```
tasks add todo buy groceries
```

This command will add a task of type todo, with the name "buy groceries".

#### Adding an Event Task

One may add a Event type task by specifying the task type, followed by the name of the task, then a fixed delimiter `-on`, followed by the date of the event.

Duke is able to handle a variety of date formats. In the event it is unable to parse the date correctly, it will use the actual input as the date, but will be unable to perform time specific commands on the event.

General Format: `tasks add event <name> -on <date>`

**Example**
```
tasks add event open the shop -on 23 Sep 15:33
```

This command will add a task of type event, with the name "open the shop" that occurs on the 23rd of September of the current year, at \3:33pm.

#### Adding an Deadline Task

One may add a Deadline type task by specifying the task type, followed by the name of the task, then a fixed delimiter `-by`, followed by the due date.

Duke is able to handle a variety of date formats. In the event it is unable to parse the date correctly, it will use the actual input as the date, but will be unable to perform time specific commands on the deadline.

General Format: `tasks add deadline <name> -by <date>`

**Example**
```
tasks add deadline remember to submit documents -by 15 Jan 19:15
```

This command will add a task of type event, with the name "remember to submit documents" that occurs on the 15th of January of the current year, at \7:15pm.

### Listing tasks

For an overview of the entire task list, input `tasks list` to list all the tasks.

Format: `tasks list`

### Annotating a task

Tasks may be marked as finished. To mark a task, specify the `mark` command, followed by the index.

General Format: `task mark <index>`

**Example**
```
tasks mark 1
```

This command will mark the 2nd task in the task list, counting from \1.


Similarly a task may be unmarked using the same format.

General Format: `tasks unmark <index>`

**Example**
```
tasks unmark 1
```

This command will unmark the 2nd task in the task list, counting from \1.

### Finding a task

We may find a task based on a substring of its name.

General Format: `tasks find <substring>`

**Example**
Assume this task list of names:
1. read book
1. renovate house
1. refurbish laptop and wireless mouse

```
tasks find re
```

This command will return results of all three tasks, as all three task names contain the substring `re`.

```
tasks find ren
```

This command will return only the second task, as only it contains the substring `ren`.

```
tasks find ouse
```

This command will return both the second and third task, as they both contain the substring `ouse`.

```
tasks find read b
```
Spaces are also valid, and the above command will return the first task.

---
## Loans

### Adding Loans
Loans can be added by specifying the `loans` command, followed by the add argument, then the name of the creditor, followed by the delimiter `-amount` and the amount owed.

General format: `loans add <creditor_name> -amount <amount_owed>` 

**Example**
```
loans add Lynette -amount $50

loans add Lynette -amount 50
```

These commands both add a loan credited to a creditor named `Lynette`, to whom is owed an amount of $50. The dollar sign in the command is optional and will be parsed out if given.

Suppose, we paid back Lynette a sum of $23. We may specify the same command.

```
loans add Lynette -amount -23
```

that subtracts $23 from the amount owed to Lynette. Now, the program records the amount that Lynette is due to be $27.

In addition, loans amounts can move into the negatives, at which it will be represented by an amount recievable.

```
loans add Adam -amount -15
```

The above command will record that Adam is due to pay $15, as Adam is currently owed an amount of -$15.


### Deleting Loans
Loans can be completely removed by specifying the `loans` command, followed by the delete argument, then the name of the creditor.

General format: `loans delete <creditor_name> ` 

**Example**
```
loans delete Lynette
```

This command will remove Lynette's loan from the collection of loans, regardless of whether she was due to pay or be paid. This command _does not_ set Lynette's amount of money owed to be zero, but instead removes the entire loan object completely from the stored loans.

### Listing Loans

The prevailing loans may be reviewed by specifying the loans command, followed by the `list` argument.

General format: `loans list`

**Example**
```
loans list
```

This command will list the loans currently recorded. 
- A person to whom an amount is owed to will have the prefix `Owed` before the amount of money.
- A person from whom an amount is payable will have the prefix `Lent` before the amount of money.

At the end of the list, a summary of receivable or payable funds will be noted.
- If the sum of all amounts is non-negative, a label `PAYABLE` will be shown before the absolute value of the sum
- If the sum of all amounts is negative, the label `RECEIVABLE` will be shown before the absolute value of the sum.

---
## Customisation

###Setting Username

By default, Duke addresses the user by the name registered as the username on the current account of the machine.

A custom name may be set with the command `set`, followed by the argument `name`, then the alias that the user will be addressed by.

General format: `set name <alias>`

**Example**
``
set name Lain
``

This command will prompt Duke to address the user as `Lain` from now on. There is no limit to how many times the alias may be changed. This command does not affect any bound data to the user.

---
## Saving

### Autosaving

Duke automatically performs saves after crucial operations that modify user data, such as adding tasks. No user interference is required.

Duke will also save the file every 5 minutes.
