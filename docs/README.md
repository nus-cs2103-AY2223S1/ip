<a name="top"></a>
# User Guide

## Table of Contents
- [About Alpha](#about_alpha)
- [Getting Started](#getting_started)
- [Features](#features)
- [Usage](#usage)
- [Command Summary](#command_summary)

## About Alpha <a name="about_alpha"></a>  
“Time isn’t the main thing. It’s the only thing.” - Miles Davis [source](https://quotefancy.com/miles-davis-quotes)

Never miss a deadline again!  
Alpha is here to help you keep a track of your tasks!

It's  
- ~~EASY~~ SUPER EASY to learn  
- ~~FUN~~ SUPER FUN to use  

Alpha is a personal chat-bot task manager that shall help you keep a track of your todos, events, and deadlines.  
It provides a range of features to help you organize your tasks in a better way.

<img src="Ui.png" width=300>

<p align="right">
<a href="#top"> [Back to Top] </a>
</p>

## Getting Started <a name="getting_started"></a>
- Ensure you have Java 11 or above installed in your Computer.
- Download the latest jar from the [release page](https://github.com/agarwal-anjali/ip/releases/download/Level-10/alpha.jar).
- Copy the file to the folder you want to use as the home folder for your Alpha.
- Make sure the directory has read and write permissions.
- Double-click the file to start the app. If it does not work then try running `java -jar alpha.jar` in a terminal.
- Enter `help` to get started!

<p align="right">
<a href="#top"> [Back to Top] </a>
</p>

## Features <a name="feature"></a>
- [x] [Get help](#get_help)

- [x] [Add tasks](#add_todo)
  - [Add todo](#add_todo)
  - [Add event](#add_event)
  - [Add deadline](#add_deadline)

- [x] [Mark task](#mark_task)

- [x] [Unmark task](#unmark_task)

- [x] [Tag task](#tag_task)

- [x] [Find tasks using keyword](#find)

- [x] [Find tasks using tag](#find_tag)

- [x] [Delete task](#delete_task)

- [x] [List all the tasks](#list)

- [x] [Exit](#exit)

<p align="right">
<a href="#top"> [Back to Top] </a>
</p>

# Usage <a name="usage"></a>

### 1. Get help <a name="get_help"></a>  
   Displays a list of acceptable commands and their required format.  
   Keyword: ```help```     
   Format: ```help```

   ```
   help
  
   COMMAND     FORMAT
   1. todo
               todo description
   2. event      
               event description /on yyyy-mm-dd
   3. deadline   
               deadline description /by yyyy-mm-dd
   4. mark       
               mark task_number
   5. unmark     
               unmark task_number
   6. tag        
               tag task_number /as tag_name
   7. find       
               find keyword
   8. findTag    
               findTag tag_name
   9. delete     
               delete task_number
   10. list      
               list
   11. bye       
               bye
   ```

### 2. Add todo <a name="add_todo"></a>
   Adds todo with the given description.  
   Keyword: ```todo```  
   Format: ```todo description```

   ```
   todo read
   
   >> added: read
   Now you have 1 task in the list!
   ```
### 3. Add event <a name="add_event"></a>
   Adds event with the given description and date.  
   Keyword: ```event```  
   Format: ```event description /on yyyy-mm-dd```
   ```
   event meeting /on 2022-12-12
  
   >> added: meeting (Dec 12 2022)
   Now you have 2 tasks in the list!
   ```
### 4. Add deadline <a name="add_deadline"></a>
   Adds a deadline with the given description and date.  
   Keyword: ```deadline```  
   Format: ```deadline description /by yyyy-mm-dd```
   ```
   deadline project /by 2022-12-12
     
   >> added: project (Dec 12 2022)
   Now you have 3 tasks in the list!
   ```

### 5. Mark task <a name="mark_task"></a>
   Marks the task corresponding to the given task number in the task list.  
   Keyword: ```mark```  
   Format: ```mark task_number```
   ```
   mark 1
  
   marked: read
   ```

### 6. Unmark Task <a name="unmark_task"></a>  
   Unmarks the task corresponding to the given task number in the task list.  
   Keyword: ```unmark```  
   Format: ```unmark task_number```
   ```
   unmark 1
  
   unmarked: read
   ```
### 7. Tag Task <a name="tag_task"></a>  
   Tags the task corresponding the given task number in the task list with the provided tag.  
   Keyword: ```tag```  
   Format: ```tag task_number /as tag_name```
   ```
   tag 2 /as important
  
   tagged: meeting (Dec 12 2020) as important
   ```
### 8. Find <a name="find"></a>  
   Finds and displays a list of tasks that contains the given keyword.  
   Keywords: ```find```  
   Format: ````find keyword````

   ```
   find pro
  
   >> Your task list is as follows:
   1. [D] [] project (by: Dec 12 2020)
   ```

### 9. FindTag <a name="find_tag"></a>  
   Finds abd displays a list of tasks that are tagged with the given tag.  
   Keyword: ```findTag```  
   Format: ```findTag tag_name```
   ```
   findTag imp
  
   >> Your task list is as follows:
   1. [E] [] [important] meeting (on: Dec 12 2020)
   ```

### 10. Delete Task <a name="delete_task"></a>  
   Deletes the task corresponding to the given task number in the task list.  
   Keyword: ```delete```  
   Format: ```delete task_number```
   ```
   #Exepcted input
   delete 1
  
   #Expected output
   deleted: read
   ```
### 11. List Tasks <a name="list"></a>  
   Displays a list of all the tasks.  
   Keyword: ```list```  
   Format: ```list```
   ```
   #Exepcted input
   list
  
   #Expected output
   >> Your task list is as follows:
   1. [E] [] [important] meeting (on: Dec 12 2020)
   2. [D] [] project (by: Dec 12 2020)
   ```

### 12. Exit <a name="exit"></a>
   Indicates the termination of the program.  
   Keyword: ```bye```  
   Format: ```bye```
   ```
   #Exepcted input
   bye
  
   #Expected output
   >> Come back later to continue from where you left!
   See you, Bye!
   ```

<p align="right">
<a href="#top"> [Back to Top] </a>
</p>

## Command Summary <a name="command_summary"></a>  

| **COMMAND**        | **FORMAT**                              |
|--------------------|-----------------------------------------|
| Help               | help                                    |
| Add Todo           | todo _description_                      |
| Add event          | event _description_ /on _yyyy-mm-dd_    |
| Add deadline       | deadline _description_ /by _yyyy-mm-dd_ |
| Mark               | mark _task_number_                      |
| Unmark             | unmark _task_number_                    |
| Tag                | tag _task_number_ /as _tag_name_        |
| Find using keyword | find _keyword_                          |
| Find using tag     | find _tag_name_                         |
| Delete             | delete _task_number_                    |
| List               | list                                    |
| Exit               | bye                                     |

<p align="right">
<a href="#top"> [Back to Top] </a>
</p>
