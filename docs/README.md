# User Guide

## Features - Adding tasks
Allows for addition of three type of tasks
1.todo task
2.event task
3.deadline task

### Feature- Deleting task

Allows for removal of a task.

### Feature- Mark/Unmark

Marks a task as done or undone.

### Feature- find

Searches for existing tasks based on a keyword.

### Feature- list

List out all tasks.

### Feature- sort

Sort the tasks according to deadline.

## Usage

### todo - Adds a todo task to the list

Adds a todo task to the list

Example of usage: 

`todo homework`

Expected outcome:

A todo homework task will be added to ur tasklist and the following output will be displayed:

```
Got it. I've added this task:
[T][] homework
Now you have 1 tasks in the list.
```

### event - Adds a event task to the list

Adds a event task to the list

Example of usage: 

`event birthday party /at 2022-09-18`

Expected outcome:

An event birthday party task will be added to ur tasklist and the following output will be displayed:

```
Got it. I've added this task:
[E][] birthday party (at Sep 18 2022)
Now you have 1 tasks in the list.
```
### deadline - Adds a deadline task to the list

Adds a deadline task to the list

Example of usage: 

`deadline homework /by 2022-09-19`

Expected outcome:

A deadline homework task will be added to ur tasklist and the following output will be displayed:

```
Got it. I've added this task:
[D][] homework (by Sep 19 2022)
Now you have 1 tasks in the list.
```

### mark - marks a task in the list as done

Marks the task at a given index as done

Example of usage: 

todo homework
mark 1

Expected outcome:

The second task on ur list will be marked as done and the following output will be displayed:

```
Nice! I've marked this task as done:
[T][X] homework
```
### unmark - unmark a task in the list as not done

Unmarks the task at a given index as not done

Example of usage: 

todo task
mark 1
unmark 1

Expected outcome:

The previously marked task will be marked as undone and the following output will be displayed:

```
OK, I've marked this task as not done yet:
[T][] homework
```

### list - list all tasks in ur tasklist

Displays all the tasks in ur tasklist

Example of usage: 

todo laundry
deadline homework /by 2022-09-22
event birthday party /at 2022-09-25

Expected outcome:

The following output will be displayed:

```
Here are the tasks in your list
1.[T][] laundry
2.[D][] homework (by:Sep 22 2022)
3.[E][] brithday party (at: Sep 25 2022)
```

### find - Find tasks by a search keyword

Displays all the tasks matching a keyword

Example of usage: 

todo laundry
deadline homework /by 2022-09-22
event birthday party /at 2022-09-25
find laundry

Expected outcome:

The bot will search for tasks that matches the input and display the following:

```
Here are the matching tasks in your list
1.[T][] laundry
```

### sort - Sort all tasks based on date

Sorts all the tasks based on the date provided.

Example of usage: 

todo laundry
deadline homework /by 2022-09-22
event birthday party /at 2022-09-25
deadline coding assignment /by 2022-08-22
sort
list

Expected outcome:

The bot will sort the tasks based on date and display the following:

```
tasks have been sorted
Here are the tasks in your list
1.[T][] laundry
2.[D][] coding assignment (by: Aug 22 2021)
3.[D][] homework (by:Sep 22 2022)
4.[E][] brithday party (at: Sep 25 2022)
```

