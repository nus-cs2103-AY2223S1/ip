
# Duke User Guide
## How to Use
For each command format, **bold** words are fixed, which means you cannot change them and their relative position. However, you can replace \<angle brackets\> with real arguments. Command word and arguments should be separated by at least one whitespace, but no end-of-line characters.
## Command List
| Command                 | Format                                                       | Description                                                                                                                                                                                                                                                                                           |
|-------------------------|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add an event task       | **event** \<description\> **/at** \<yyyy-MM-dd HH:mm:ss\>    | Add an event that takes place at year **_yyyy_**, month **_MM_**, day **_dd_**, hour **_HH_**, minute **_mm_**, and second **_ss_**. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank. |
| Add a deadline task     | **deadline** \<description\> **/by** \<yyyy-MM-dd HH:mm:ss\> | Add a deadline at year **_yyyy_**, month **_MM_**, day **_dd_**, hour **_HH_**, minute **_mm_**, and second **_ss_**. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank.                |
| Add a to-do task        | **todo** \<description\>                                     | Add a to-do task that does not have a specific deadline to meet or a specific date and time. It just has to be done some time. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank.       |
| List all tasks saved    | **list**                                                     | List all tasks, along with basic information about them. You can see the status (whether it is done or not) and date and time (if any). Note that date and time will be displayed in a different format (**yyyy/MM/dd HH:mm:ss**) from that one in which you input.                                   |
| Delete a task           | **delete** \<index\>                                         | Delete the task corresponding to \<index\>, which should be a one-based integer index. After this operation, you no longer see this task in the list.                                                                                                                                                 |
| Mark a task as done     | **mark** \<index\>                                           | Mark the task corresponding to \<index\> as done. \<index\> should be a one-based integer index. Done tasks are indicated by an "X"                                                                                                                                                                   |
| Mark a task as not done | **unmark** \<index\>                                         | Mark the task corresponding to \<index\> as not done. \<index\> should be a one-based integer index. Undone tasks are indicated by a whitespace.                                                                                                                                                      |
| Sort the list           | **sort**                                                     | Sort the tasks in a ascending chronological order. Tasks without date and time, such as to-do tasks are sorted to the end of list. More urgent tasks are closer to the top, for your convenience.                                                                                                     |
| Find some tasks         | **find** \<keyword\>                                         | List all tasks that contain the \<keyword\>, which can be arbitrarily long and can have whitespaces.                                                                                                                                                                                                  |
| Exit                    | **bye**                                                      | Save the list and exit the program immediately.                                                                                                                                                                                                                                                       |

## Features
### 1. Add a task
Refer to the command list [here](#command-list). You can use commands `todo`, `event`, and `deadline` to add a new task.
* A to-do task is a task without specific due date.
* An event task is a task that takes place exactly at the date and time you indicate.
* A deadline task is a task that must be done some time before the stipulated deadline.
#### Example of usage
```  
todo read a book
event orbital splashdown /at 2022-08-22 13:00:00 
deadline CS2103T iP Week 4 /by 2022-08-27 18:00:00 
```  
#### Expected outcome
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
```
### 2. Auto-save
The app will save the list to `.\saved_list.txt` every time there is a change to the list.
#### Example of usage
```
event NUS Internship Day /at 2022-09-14 16:00:00  
deadline CS2103T iP Submission /by 2022-09-16 23:59:59  
```
#### Expected outcome
In `saved_list.txt`  that locates at the same folder as `duke.jar` file.
```
E | 0 | NUS Internship Day | 2022/09/14 16:00:00 D | 0 | CS2103T iP Submission | 2022/09/16 23:59:59  
```
### 3. List all tasks
Refer to the command list [here](#command-list). You can use command `list` to see all tasks you saved.
#### Example of usage
```
todo read a book
event orbital splashdown /at 2022-08-22 13:00:00 
deadline CS2103T iP Week 4 /by 2022-08-27 18:00:00 
list 
```
#### Expected outcome
```
// ...

-------------------------  
    1. [ ] [T] read a book  
    2. [ ] [E] orbital splashdown at 2022/08/22 13:00:00  
    3. [ ] [D] CS2103T iP Week 4 by 2022/08/27 18:00:00  
------------------------- 
```
### 4. Delete a task
Refer to the command list [here](#command-list). Use `delete` to remove any unwanted task.
#### Example of usage
```
delete 3  
```
#### Expected outcome
```
-------------------------    
    Sure, I have removed this task from the list:    
    [ ] [D] CS2103T iP Week 4 by 2022/08/27 18:00:00    
	There are 2 tasks in the list. 
-------------------------  
```

### 5. Find a task
Refer to the command list [here](#command-list). `find` is for you to look up any task in the list. Key in a keyword for `Duke` to look for all tasks whose description contain the keyword.
#### Example of usage
```
find r
```
#### Expected outcome
```
-------------------------    
    Here are what I found:    
	1. [ ] [T] read a book    
	2. [ ] [E] orbital splashdown at 2022/08/22 13:00:00 
-------------------------  
```
Note that the two tasks contain 'r'.

### 6. Mark a task
Refer to the command list [here](#command-list). You can use `mark` to mark a task as done and `unmark` to mark a task as not done yet.  Done tasks are indicated by an “X” surrounded by a pair of square brackets, while undone tasks are indicated by a pair of empty square brackets.
#### Example of usage
```
mark 2  
unmark 2  
```
#### Expected outcome
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

### 7. Sort
You can use `sort` to sort the tasks in chronological order from early to late. The reason early tasks are put at the top is to aid you identify which tasks are more urgent.
#### Example of usage
```  
todo read a book 
event orbital splashdown /at 2022-08-22 13:00:00 
deadline CS2103T iP Week 4 /by 2022-08-27 18:00:00  
sort  
```  
#### Expected outcome
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
In case of unknown commands, incorrect format, invalid arguments, or unexpected behaviours, the app will send you a message. Simply follow the instruction to tackle the problem.

For example, if you want to `mark` a task that has an out-of-bound index, `Duke` will  remind you of the format.
```
// Input:
// Suppose there are 3 tasks in the list
mark 4

// Output:
-------------------------  
    Oops! Do check the index range, and the format should be "mark <index>"  
-------------------------
```
If any problem persists, feel free to send in an email to [e0735467@u.nus.edu](e0735467@u.nus.edu).