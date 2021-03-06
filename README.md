# Employee Reimbursement System 

## Project Description

Expense reimbursement system for employees to request expense reimbursement to be approved or denied by managers. Managers can leave comments concerning their approval or denial of expense reimbursement requests and managers can view which managers approved or denied each request in a request history. 

## Technologies Used

* Java - version 8
* JDBC
* JavaScript

## Features
* Managers can review list of all reimbursement requests resolved or pending and can see the manager who resolved the request
* Employees can update their personal information such as phone number and email address
* Managers can leave comments for the requests they resolve that other managers can see 

To-do list:
* When managers resolve requests, add another time column that shows when the request was resolved
* Improve the incorrect login credentials to make a toaster pop-down instead of using the alert function 

## Getting Started
   
(1) Create a free AWS RDS instance

(2) Clone this repo (copy and paste): git clone https://github.com/wolfman30/project1.git

(3) Go to the DatabaseConnector class and change this information in the getConnection() method: 
         
         * String url = "jdbc:postgresql://javafs-210907-rds.csznvnr7exd6.us-east-2.rds.amazonaws.com:5432/postgres";
         * String username = "postgres";
         * String password = "2022AmazingCareer";
        
        * replace all three properties (url, username, and password) with information to connect to your own AWS instance. 
        
(4) You need to create two tables with a few views, the SQL commands. 
     
   To create the table for users:
   
         create table ers_user (
               id serial primary key,
               first_name varchar(40),
               last_name varchar(80), 
               pass_word text, 
               email text, 
               user_type varchar(30), 
               phone_number numeric(10)
               ); 
               
   To create table for expense reimbursement history: 
         
         create table reimbursement_request_history (
            time_created timestamp not null, 
            employee_id int,
            amount numeric(10, 2), 
            reason text, 
            status varchar(20),
            approved boolean, 
            foreign key(employee_id) 
            references ers_user(id)
            ); 

      
   
 

## Demonstration

- To see a demo videos, download and watch the videos in the demo-videos folder 

## License

Free to use by anybody. 


