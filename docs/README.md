# Duke User Guide
## How to Use
For each command format, **bold** words are fixed, which means you cannot change them and their relative position. However, you can replace \<angle brackets\> with real arguments. Command word and arguments should be separated by at least one white space, but no end-of-line characters.
## Command List
| Command | Format | Description |
|----|----|----|
| Add an event task | **event** \<description\> /at \<yyyy-MM-dd HH:mm:ss\> | Add an event that takes place at year **_yyyy_**, month **_MM_**, day **_dd_**, hour **_HH_**, minute **_mm_**, and second **_ss_**. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank. |
| Add a deadline task | **deadline** \<description\> /by \<yyyy-MM-dd HH:mm:ss\> | Add a deadline at year **_yyyy_**, month **_MM_**, day **_dd_**, hour **_HH_**, minute **_mm_**, and second **_ss_**. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank. |
| Add a to-do task | **todo** \<description\> | Add a to-do task that does not have a specific deadline to meet or a specific date and time. It just has to be done some time. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank. |
| List all tasks saved | **list** | List all tasks, along with basic information about them. You can see the status (whether it is done or not) and date and time (if any). Note that date and time will be displayed in a different format (**yyyy/MM/dd HH:mm:ss**) from that one in which you input. |
| Delete a task | **delete** \<index\> | Delete the task corresponding to \<index\>, which should be a one-based integer index. After this operation, you no longer see this task in the list. |
| Mark a task as done | **mark** \<index\> | Mark the task corresponding to \<index\> as done. \<index\> should be a one-based integer index. Done tasks are indicated by an "X" |
| Mark a task as not done | **unmark** \<index\> | Mark the task corresponding to \<index\> as not done. \<index\> should be a one-based integer index. Undone tasks are indicated by a whitespace. |
| Sort the list | **sort** | Sort the tasks in a ascending chronological order. Tasks without date and time, such as to-do tasks are sorted to the end of list. More urgent tasks are closer to the top, for your convenience. |
| Find some tasks | **find** \<keyword\> | List all tasks that contain the \<keyword\>, which can be arbitrarily long and can have whitespaces. |
| Exit | **bye** | Save the list and exit the program immediately. |

## Features
### Auto-save
The app will save the list to `.\saved_list.txt` every time there is a change to the list.
#### Sample outcome
Input:
```
event NUS Internship Day /at 2022-09-14 16:00:00
deadline CS2103T iP Submission /by 2022-09-16 23:59:59
```
Output in `saved_list.txt`
```
E | 0 | NUS Internship Day | 2022/09/14 16:00:00  
D | 0 | CS2103T iP Submission | 2022/09/16 23:59:59
```
### Add, find, and delete a task
Refer to the command list [here](#command-list). You can use commands `todo`, `event`, and `deadline` to add a new task. `find` is for you to look up any task in the list. Use `delete` to remove any unwanted task.
#### Sample outcome
Input:
```
todo read a book  
event orbital splashdown /at 2022-08-22 13:00:00  
deadline CS2103T iP Week 4 /by 2022-08-27 18:00:00  
list  
find r
delete 3
list
```
Output:
```
  
-------------------------  
    Added: [ ] [T] read a book  
-------------------------  
  
  
-------------------------  
    Added: [ ] [E] orbital splashdown at 2022/08/22 13:00:00  
-------------------------  
  
  
-------------------------  
    Added: [ ] [D] CS2103T iP Week 4 by 2022/08/27 18:00:00  
-------------------------  
  
  
-------------------------  
    1. [ ] [T] read a book  
    2. [ ] [E] orbital splashdown at 2022/08/22 13:00:00  
    3. [ ] [D] CS2103T iP Week 4 by 2022/08/27 18:00:00  
-------------------------
  
-------------------------  
    Here are what I found:  
    1. [ ] [T] read a book  
    2. [ ] [E] orbital splashdown at 2022/08/22 13:00:00  
-------------------------

-------------------------  
    Sure, I have removed this task from the list:  
    [ ] [D] CS2103T iP Week 4 by 2022/08/27 18:00:00  
    There are 2 tasks in the list.  
-------------------------
```

### Mark a task
Refer to the command list [here](#command-list). You can use `mark` to mark a task as done and `unmark` to mark a task as not done yet.
#### Sample outcome
Input:
```
mark 2
unmark 2
```
Output:
```
-------------------------  
    Good to hear that! I have marked this as done:  
    [X] [E] orbital splashdown at 2022/08/22 13:00:00  
-------------------------  

-------------------------  
    Sure, I have marked this as not done yet.  
    [ ] [E] orbital splashdown at 2022/08/22 13:00:00  
-------------------------
```

### Sort
You can use `sort` to sort the tasks in chronological order from early to late. The reason early tasks are put at the top is to aid you identify which tasks are more urgent.
#### Sample outcome
Input:
```
todo read a book 
event orbital splashdown /at 2022-08-22 13:00:00 
deadline CS2103T iP Week 4 /by 2022-08-27 18:00:00
sort
```
Output:
```
// ...

-------------------------
    Sure! I have sorted the list!
    1. [ ] [E] orbital splashdown at 2022/08/22 13:00:00
    2. [ ] [D] CS2103T iP Week 4 by 2022/08/27 18:00:00
    3. [ ] [T] read a book
-------------------------

```

## Troubleshooting
In case of unknown commands, incorrect format, invalid arguments, or unexpected behaviours, the app will send you a message. Simply follow the instruction to tackle the problem. If the problem persists, feel free to send in an email to e0735467@u.nus.edu.