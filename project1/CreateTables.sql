create table students (
    snum integer not null,
    ssn integer not null,
    name varchar(20),
    gender varchar(1),
    dob varchar (10),
    c_addr varchar(20),
    c_phone varchar(10),
    p_addr varchar(20),
    p_phone varchar(10),
    primary key (ssn),
    unique (snum)
);

create table departments (
    code integer not null,
    name varchar(50) not null,
    phone varchar(10),
    college varchar(20),
    primary key (code),
    unique (name)
);

create table degrees (
    name varchar(50) not null,
    level varchar(5) not null,
    department_code integer,
    primary key (name, level),
    foreign key (department_code) references departments(code)
);

create table courses (
    number integer not null,
    name varchar(50),
    description varchar(50),
    credithours integer,
    level varchar(20),
    department_code integer,
    primary key (number),
    foreign key (department_code) references departments(code)
);

create table register (
    snum integer not null,
    course_number integer not null,
    regtime varchar(20),
    grade integer,
    primary key (snum, course_number),
    foreign key (snum) references students(snum),
    foreign key (course_number) references courses(number)
);

create table major (
    snum integer not null,
    name varchar(50) not null,
    level varchar(5) not null,
    primary key (snum, name, level),
    foreign key (snum) references students(snum),
    foreign key (name, level) references degrees(name, level)
);

create table minor (
    snum integer not null,
    name varchar(50) not null,
    level varchar(5) not null,
    primary key (snum, name, level),
    foreign key (snum) references students(snum),
    foreign key (name, level) references degrees(name, level)
);
