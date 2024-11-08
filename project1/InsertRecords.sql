LOAD DATA INFILE 'C:\Users\Moldy\Downloads\data\departments.csv'
INTO TABLE departments
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(code,name,phone,college);

LOAD DATA INFILE 'C:\Users\Moldy\Downloads\data\students.csv'
INTO TABLE students
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(snum,ssn,name,gender,dob,c_addr,c_phone,p_addr,p_phone);