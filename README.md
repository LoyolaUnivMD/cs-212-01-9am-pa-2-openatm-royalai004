# CS 212 - Programming Assignment 2
Design Due: Fri 2/23 by 11:59pm
Final Due: 3/01 by 11:59 pm

## Summary
For this assignment you will add some functionality to your ATM from lab 3. Your ATM will now support multiple accounts and even let you view your account history.

## Description
For this assignment, you are going to create an OpenATM. The ATM will behave as follows (**NOTE:** this is not a proper algorithm):

1. The ATM asks for a user's first and last name or "exit"  
2. If the user selects exit  
    1. End the program  
3. Look up the users account number.  
4. If the user does not exist and there are unused accounts.   
    1. A new account is created for them.  
5. Otherwise, if there are no spaces remaining:  
	1. Go to step 1  
6. Otherwise,  
	1. The users account is retreived.  
7. The user's account number is displayed.  
8. The user is provided a menu to *withdraw*, *deposit*, *get statistics*, *view recent transactions*, or *leave*.  
9. While the user has not chosen to leave:  
    1. If a user selects **deposit**     
        1. The ATM should increase the user's balance by the provided positive amount    
    2. Otherwise, if a user selects **withdraw**    
        1. The ATM should reduce the balance by the users provided positive amount if and only if there are sufficients funds.  
    3. Otherwise, if the user selects **get statistics**  
        1. The ATM should display the current balance, as well as the min, max, and average transaction sizes of the last 5 transactions.  
	4. Otherwise, if the user selects **view recent transactions**  
	    1. The ATM should show the user their last 5 deposits or withdrawals (or fewer if they have not made 5 yet).  
10. Go to step 1  




## Programming Requirements

### Account Class
For this assignment, you will create a class that encapsulates a bank account. It is up to you how to design and implement the **Account** class. However, it should have the following minimum requirements:

#### Data Fields (Instance variables)

1. An array to track the previous 5 transactions  
2. A string for the name of the account owner  
3. *Any other data members you thing you might need.*  

#### Methods

1. A constructor with the number of recent transactions to store, an intital deposit, and the name of the owner.  
2. `isOwner` method to determine if an account is owned by a given person.  
3. `deposit` method to intitate a deposit  
4. `withdraw` method to intitate a withdrawal  
5. `getStats` method to return an array of statistics  
6. `toString` or `display` method to assist in printing our account information  

#### Error checking
You should do at least the following error checking:
1. The user should not be able to select invalid menu options.  
2. The user should not be able to enter negative numbers or zero for deposits or withdrawls.  
3. The program should not crash if the user enters something that is not a number. 

### Main

You will also write a client class (Main) to use the `Account` class. The `main` program should behave as described above. 

#### Additional details
* The ATM supports 5 customers which should be stored in an array.  
* The "account number" is the possition of the Account in the ATM's array.


### Other classes
You can create other classes to support the ATM as you see fit.

### Extra Credit
Do not attempt this until everything else is working.

Add a secret menu option: *hacker*. When this option is selected it should print out the statistics of all the account owners sorted from greatest bank balance to least. It should do this without changing the order of the account array so as not to change the account numbers.

## Submission

### Part 1 (10 points)
Design and algorithm. (no need to write any code). Due 3/3

Submit to Github:

1. *design.txt* describing what is in your **Account** class, including:  
    * data type of the instance variables
	* return type and list of parameters(if any) for the constructor(s) and methods
2. *testcases.txt* describing at least 5 test cases

### Part 2 (50 points)

Submit to Github:
1. All files neccessary to run your program. Comments should be in proper Javadoc format.

2. An updated version of *design.txt* that addresses any comments.

3. A **reflection.txt** containing a reflection (min 200 words) that includes answers to at least the following:
    1. What is the most challenging thing you experienced?
    2. How did you overcome it?
    3. Anything you would do differently given what you know now.
	4. Was it helpful to build on a project that you already built in lab?
	5. Are you becoming more comfortable with classes?
	6. How did you feel about arrays? Were there things about them that you would like to overcome?


*Yes, I've started checking word count. Kindly do the same - Mr. John*
