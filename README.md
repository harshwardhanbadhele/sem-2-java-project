# sem-2-java-project
program to manage university course according to user role

--------------------------------------------------------------------------------------------------------

 FLOW OF CODE :-
1) Start → Main.main() runs → Database.loadData() (loads previous data if exists).
Main Menu:
Option 1 → Login
Option 2 → Enroll New Student
Option 3 → Exit + Save Data

2) Login Flow:
User chooses role (Student / Professor / Admin)
Credentials checked
For Admin → authenticate() checks fixed password
Successful login → calls showMenu() of respective class

3) Role-based Menus:
Student: View courses, Register, Drop, Submit Complaint, View Complaints, Track Progress
Professor: Manage Courses, View Enrolled Students, Assign Grades
Administrator: Manage Course Catalog, Manage Students, Assign Professor to Course, Handle Complaints

4) Data Flow:
All data stored in Database (Singleton pattern)
Changes made during runtime are saved on exit using serialization

5) Exit → Database.saveData() is called → All objects are written to file.


--------------------------------------------------------------------------------------------------------

CONCEPTS USED:-

Class:
Course, Database, Main, Feedback<T> are normal classes.
Student, Professor, Administrator are user classes that extend Course.

Inheritance (extends):
Student, Professor, AdministratorextendCourse class (as per your requirement).
AssistantTutor extends Student.

Interface:
UserRole interface is implemented by Course (and indirectly by all user types).
It forces all roles to implement showMenu() and logout().

Polymorphism:
showMenu() is overridden in Student, Professor, and Administrator.
Same method call behaves differently based on the actual object type.

Encapsulation:
Most fields are private.
Getters and setters (like getComplaints(), addGrade()) are used to access data.

Abstraction:
UserRole interface provides abstraction.
showMenu() hides internal implementation from Main class.

Custom exceptions created:-

WrongPasswordException
CourseFullException

PrerequisiteNotMetException

AlreadyEnrolledException

DropDeadlineExceededException

Serialization: Used to save and load entire system state.
Database class implements Serializable.
saveData() → Writes object to university_data.ser file using ObjectOutputStream.
loadData() → Reads object using ObjectInputStream.


Collection:-
ArrayList : enrolledCourses, coursesTaught
HashMap : grades, complaints, students/professors/admins
Map.Entry :Iterating over complaints map


--------------------------------------------------------------------------------------------------------


EXAMPLE CODE RUN:-

Previous data loaded.
=== Welcome to University Course Registration System ===

1. Enter the Application
2. Enroll New Student
3. Exit the Application
2
Student ID: S002
Full Name: harshvardhan baghele
Email: harsh@uni.edu
Password: harsh123
Semester: 1
A student with this email already exists.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
2
Student ID: S003
Full Name: dhruvi varia
Email: dhruvi@uni.edu
Password: dhruvi123
Semester: 1
Student enrolled successfully: dhruvi varia (dhruvi@uni.edu)

1. Enter the Application
2. Enroll New Student
3. Exit the Application
2
Student ID: S004
Full Name: anandraj
Email: anandraj@uni.edu
Password: anandraj123
Semester: 1
Student enrolled successfully: anandraj (anandraj@uni.edu)

1. Enter the Application
2. Enroll New Student
3. Exit the Application
2
Student ID: S005
Full Name: aarohi
Email: aarohi@uni.edu
Password: aarohi123
Semester: 1
Student enrolled successfully: aarohi (aarohi@uni.edu)

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
3
Email: admin@uni.edu
Password: admin123

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 1

=== Manage Course Catalog ===
1. View All Courses
2. Add New Course
3. Delete Course
4. Back to Main Menu
Enter choice: 2
Enter Course Code: DS101
Enter Course Title: Data structure
Enter Credits (2 or 4): 4
New course added successfully!

=== Manage Course Catalog ===
1. View All Courses
2. Add New Course
3. Delete Course
4. Back to Main Menu
Enter choice: 1

--- All Courses ---
CS101 | Introduction to Programming | Credits: 4 | Timings: 5:00 pm
AI101 | Artificail Intelligence | Credits: 4 | Timings: TBD
DS101 | Data structure | Credits: 4 | Timings: TBD

=== Manage Course Catalog ===
1. View All Courses
2. Add New Course
3. Delete Course
4. Back to Main Menu
Enter choice: 4

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 3

=== Assign Professor to Course ===
Available Courses:
CS101 - Introduction to Programming
AI101 - Artificail Intelligence
DS101 - Data structure

Enter Course Code: AI101

Available Professors:
P001 - Dr. Sharma
Enter Professor ID: P001
Course AI101 has been assigned to Professor Dr. Sharma

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 3

=== Assign Professor to Course ===
Available Courses:
CS101 - Introduction to Programming
AI101 - Artificail Intelligence
DS101 - Data structure

Enter Course Code: DS101

Available Professors:
P001 - Dr. Sharma
Enter Professor ID: P001
Course DS101 has been assigned to Professor Dr. Sharma

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 2

=== Student Records ===
harsh@uni.edu - harshvardhan baghele | Semester: 1
S003 - dhruvi varia | Semester: 1
S005 - aarohi | Semester: 1
T001 - Assistant Tutor Raj | Semester: 1
S004 - anandraj | Semester: 1
S001 - Harsh Student | Semester: 1

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 4

=== Pending Student Complaints ===
No pending complaints at the moment.

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 5
Logged out successfully. Changes saved.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
1
Email: dhruvi@uni.edu
Password: dhruvi123

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 1

=== Available Courses for Semester 1 ===
CS101 - Introduction to Programming (4 credits)
AI101 - Artificail Intelligence (4 credits)
DS101 - Data structure (4 credits)

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 3

=== Your Current Schedule ===
No courses enrolled yet.

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 2
Enter Course Code: AI101
Successfully registered for AI101

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 2
Enter Course Code: DS101
Successfully registered for DS101

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 8
Logged out successfully. Changes saved.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
1
Email: aarohi@uni.edu
Password: aarohi123

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 2
Enter Course Code: DS101
Successfully registered for DS101

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 2
Enter Course Code: AI101
Successfully registered for AI101

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 2
Enter Course Code: CS101
Successfully registered for CS101

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 8
Logged out successfully. Changes saved.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
1
Email: anadraj@uni.edu
Password: anandraj123
Invalid Student credentials.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
1
Email: anandraj@uni.edu
Password: anandraj123

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 2
Enter Course Code: CS101
Successfully registered for CS101

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 2
Enter Course Code: DS101
Successfully registered for DS101

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 6
Enter complaint description: too few courses available,petition for drawing and EVS courses
Complaint submitted successfully. Status: Pending

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 7

=== Your Complaints ===
1. too few courses available,petition for drawing and EVS courses → Pending

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 8
Logged out successfully. Changes saved.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
2
Email: prof@uni.edu
Password: prof123

=== PROFESSOR MENU ===
1. Manage Courses
2. View Enrolled Students
3. Assign Grades
4. Logout
Enter choice: 1

=== Your Courses ===
Code: CS101 | Title: Introduction to Programming | Credits: 4 | Timing: 5:00 pm | Location: A101
Update timings? (y/n): n
Code: AI101 | Title: Artificail Intelligence | Credits: 4 | Timing: TBD | Location: TBD
Update timings? (y/n): y
New timings: 4:00 pm
Code: DS101 | Title: Data structure | Credits: 4 | Timing: TBD | Location: TBD
Update timings? (y/n): y
New timings: 3:00 pm

=== PROFESSOR MENU ===
1. Manage Courses
2. View Enrolled Students
3. Assign Grades
4. Logout
Enter choice: 2
Enter course code: AI101

=== Enrolled Students in AI101 ===
- harshvardhan baghele (harsh@uni.edu)
- dhruvi varia (dhruvi@uni.edu)
- aarohi (aarohi@uni.edu)

=== PROFESSOR MENU ===
1. Manage Courses
2. View Enrolled Students
3. Assign Grades
4. Logout
Enter choice: 3

=== Assign Grade ===
Enter Student Email: aarohi@uni.edu
Enter Course Code: AI101
Enter Grade (A/B/C/D/F): C
Grade C assigned for course AI101
Grade successfully updated in student's record.

=== PROFESSOR MENU ===
1. Manage Courses
2. View Enrolled Students
3. Assign Grades
4. Logout
Enter choice: 3

=== Assign Grade ===
Enter Student Email: anandraj@uni.edu
Enter Course Code: CS101
Enter Grade (A/B/C/D/F): F
Grade F assigned for course CS101
Grade successfully updated in student's record.

=== PROFESSOR MENU ===
1. Manage Courses
2. View Enrolled Students
3. Assign Grades
4. Logout
Enter choice: 2
Enter course code: CS101

=== Enrolled Students in CS101 ===
- aarohi (aarohi@uni.edu)
- anandraj (anandraj@uni.edu)
- Harsh Student (student@uni.edu)

=== PROFESSOR MENU ===
1. Manage Courses
2. View Enrolled Students
3. Assign Grades
4. Logout
Enter choice: 4
Logged out successfully. Changes saved.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
3
Email: admin@uni.edu
Password: admin123

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 4

=== Pending Student Complaints ===

Student     : anandraj
Email       : anandraj@uni.edu
Complaint   : too few courses available,petition for drawing and EVS courses
Status      : Pending

Enter resolution for this complaint: not possible for drawing but we can discuss with authority about EVS course
Complaint resolved successfully.
Resolution : not possible for drawing but we can discuss with authority about EVS course
-----------------------------------

=== ADMINISTRATOR MENU ===
1. Manage Course Catalog
2. Manage Student Records
3. Assign Professor to Course
4. Handle Complaints
5. Logout
Enter choice: 5
Logged out successfully. Changes saved.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
1

Login as:
1. Student
2. Professor
3. Administrator
1
Email: anandraj@uni.edu
Password: anandraj123

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 7

=== Your Complaints ===
1. too few courses available,petition for drawing and EVS courses → Resolved

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 4

=== Academic Progress (Semester 1) ===
Grades: {CS101=F}

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 3

=== Your Current Schedule ===
- CS101 | Introduction to Programming | Timing: 5:00 pm | Location: A101 | Professor: Dr. Sharma
- DS101 | Data structure | Timing: 3:00 pm | Location: TBD | Professor: TBA

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 5
Enter Course Code to drop: CS101
Error: Drop deadline has passed. Cannot drop course now.

=== STUDENT MENU ===
1. View Available Courses
2. Register for Course
3. View Schedule
4. Track Academic Progress
5. Drop Course
6. Submit Complaint
7. View My Complaints
8. Logout
Enter choice: 8
Logged out successfully. Changes saved.

1. Enter the Application
2. Enroll New Student
3. Exit the Application
