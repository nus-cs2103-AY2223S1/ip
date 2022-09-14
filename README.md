# Duke - Your virtual assistant
![alt text](https://github.com/GarethOng/ip/blob/1334b2beda4d5f59c2cfb14353069f51d0b3160e/docs/Ui.png)
> "For every minute spent organizing, an hour is earned." -Benjamin Franklin

## Introduction
Duke is your **simple** solution to 
becoming the organized person that you dreamt of being.
Keep all your todos, deadlines and events with Duke and never have to worry about being organised again.

## Features
- Track ToDos, Deadlines and Events
- Mark and unmark task status as done
- List out all existing task
- Delete task
- Sort task in chronological or lexicographical order

## Getting Started
1. Download the latest release [here](https://github.com/GarethOng/ip/releases)
2. Simply double-click the jar file to get started!
3. alternatively you can run the following in your device's CLI:
```
java -jar duke.jar
```
## Feature: Adding ToDo
To add a ToDo task:
```
todo [task's description]
```

For example to add a todo task for doing homework:
```
todo homework
```

## Feature: Adding Deadline
To add a Deadline task:
```
deadline [task's description] /by [date in YYYY-MM-DD hh:mm]
```

For example to add an ip submission by 17/9/2022 5:59 am:
```
deadline ip submission /by 2022-17-09 05:59
```

## Feature: Adding Event
To add a Deadline task:
```
event [task's description] /at [date in YYYY-MM-DD hh:mm]
```

For example to add a CS2101 presentation at 12/9/2022 8:00 am:
```
event CS2101 presentation /at 2022-12-09 08:00
```

## Feature: List
To get a list of all tasks added:
```
list
```
which will display:
```
1. [T][]homework
2. [D][]ip submission (by:Sep 17 2022, 5:59 am)
3. [E][]CS2101 presentation (at: Sep 12 2022, 8:00 am)
```

## Feature: Mark task as done
Given the above list, you can perform:
```
mark 2
```
To mark the second task as done,
```
1. [T][]homework
2. [D][X]ip submission (by:Sep 17 2022, 5:59 am)
3. [E][]CS2101 presentation (at: Sep 12 2022, 8:00 am)
```

## Feature: Unmark task as done
Given the above list, you can perform:
```
unmark 2
```
To unmark the second task as done,
```
1. [T][]homework
2. [D][]ip submission (by:Sep 17 2022, 5:59 am)
3. [E][]CS2101 presentation (at: Sep 12 2022, 8:00 am)
```

## Feature: Delete
Suppose we want to delete the second task in the above list:
```
delete 2
```
Which results in our list becoming:
```
1. [T][]homework
2. [E][]CS2101 presentation (at: Sep 12 2022, 8:00 am)
```

## Feature: Sorting Chronologically
You can specify if you want to sort either the events or the 
deadlines in the list in either an increasing or decreasing manner:
```
sort [deadline/ event] chrono [increasing/ decreasing]
```

Suppose we want to sort the deadlines in the following list of task:
```
1. [D][]homework 4 (by:Sep 22 2022, 5:59 am)
2. [D][]homework 1 (by:Sep 15 2022, 5:59 am)
3. [D][]homework 3 (by:Sep 19 2022, 5:59 am)
4. [D][]homework 2 (by:Sep 17 2022, 5:59 am)
```
We just need to input the following:
```
sort deadlines chrono increasing
```
which will return:
```
1. [D][]homework 1 (by:Sep 15 2022, 5:59 am)
2. [D][]homework 2 (by:Sep 17 2022, 5:59 am)
3. [D][]homework 3 (by:Sep 19 2022, 5:59 am)
4. [D][]homework 4 (by:Sep 22 2022, 5:59 am)
```

## Feature: Sorting Lexicographically
You can specify if you want to sort either the events or the
deadlines in the list in either an increasing or decreasing manner:
```
sort [deadline/ event] lexi [increasing/ decreasing]
```

Suppose we want to sort the deadlines in the following list of task:
```
1. [D][]def (by:Sep 22 2022, 5:59 am)
2. [D][]cde (by:Sep 15 2022, 5:59 am)
3. [D][]abc (by:Sep 19 2022, 5:59 am)
4. [D][]efg (by:Sep 17 2022, 5:59 am)
```
We just need to input the following:
```
sort deadlines lexi increasing
```
which will return:
```
1. [D][]abc (by:Sep 19 2022, 5:59 am)
2. [D][]cde (by:Sep 15 2022, 5:59 am)
3. [D][]def (by:Sep 22 2022, 5:59 am)
4. [D][]efg (by:Sep 17 2022, 5:59 am)
```
