# BankSystem
Zhizhou Qiu
![Test Pic](file:///Users/qizhizho/Desktop/document%20choice@Amazon.png)
## How to Use:

```
git clone https://github.com/qzhizhou/BankSystem.git
cd Bank/src
javac Main.java
java Main
```

## User Interface:    
### Home Page
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/homepage.png)
This is the HomePage that will pop up once the program starts running.              
Functionality: user login, open a bank account, change password, manager login                    
                   
### Open Account Page                 
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/open_account.png)
This is the page to open a bank account.             
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/create_account_successfully.png)         
By default, the id that is created is both user name and password.           
              
### Change Password Page                  
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/chage_password.png)              
Once your account is created successfully, you can change the password at this page.                

### Manager Login Page
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/manager_login.png)
This is the page for manager to login.                      
By default, login name is Christine, password is 100. Password could be changed in the change_password_page.          

### User Main Page
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/customer_mainpage.png)
Once user logins, this main page will show up, where user could operate their account or get transactions.

### Customer Operation Page
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/customer_operation_page.png)
At this page, a user could make deposits, withdraw, request loans and pay off dept.             
By default, making deposits from checking account or withdrawing would cost 5 dollars.          
The current loan interest is 2, so if a user request 100 dollar loan, then he/she needs to pay off 200 dollar.          
              
### Customer Transaction Page              
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/customer_get_information_page.png)
At this page, a user could view his/her transactions.               
             
### Manager Operation Page
![](https://github.com/qzhizhou/BankSystem/blob/master/pic/manager_operation_page.png)
At this page, a manager is able to view all the transactions as well as transactions of a certain customer.       
And daily bank income information is alos available to reach. 

## Architecture:

>Bank
>>ATM, Employee
>>>Manager    

* Bank is the super class, it contains attribute of customer information and manager information.     
* ATM is a subclass of Bank. It mainly support functionalities that are shown in the user interface.     
* Employee is a subclass of Bank.
* Manager is a subclass of Employee.

