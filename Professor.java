package com.university.professor;

import com.university.model.Course;
import com.university.model.Database;
import com.university.model.UserRole;
import com.university.student.Student;

import java.io.Serializable;
import java.util.*;

public class Professor extends Course implements UserRole, Serializable 
{
    private static final long serialVersionUID = 1L;
    private List<String> coursesTaught = new ArrayList<>();

    public Professor(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void manageCourses(Scanner sc, List<Course> catalog) {
        System.out.println("\n=== Your Courses ===");
        if (coursesTaught.isEmpty()) {
            System.out.println("No courses assigned yet.");
            return;
        }
        for (String code : coursesTaught) 
        {
            for (Course c : catalog) 
            {
                if (c.getCourseCode() != null && c.getCourseCode().equals(code)) 
                {
                    System.out.println(c.getDetails());
                    System.out.print("Update timings? (y/n): ");
                    if (sc.nextLine().equalsIgnoreCase("y")) 
                    {
                        System.out.print("New timings: ");
                        c.setTimings(sc.nextLine());
                    }
                }
            }
        }
    }

    public void assignCourse(String courseCode) {
        if (!coursesTaught.contains(courseCode)) {
            coursesTaught.add(courseCode);
            System.out.println("Course " + courseCode + " has been assigned to Professor " + getName());
        } else {
            System.out.println("Course " + courseCode + " is already assigned.");
        }
    }

    public List<String> getCoursesTaught() {
        return coursesTaught;
    }

    public void viewEnrolledStudents(String courseCode) {
        System.out.println("\n=== Enrolled Students in " + courseCode + " ===");
        boolean found = false;
        for (Student s : Database.getInstance().students.values()) {
            if (s.getEnrolledCourses().contains(courseCode)) {
                System.out.println("- " + s.getName() + " (" + s.getEmail() + ")");
                found = true;
            }
        }
        if (!found) System.out.println("No students enrolled in this course.");
    }

    public void assignGrade(Scanner sc) {
        Database db = Database.getInstance();
        System.out.println("\n=== Assign Grade ===");

        System.out.print("Enter Student Email: ");
        String email = sc.nextLine();
        Student student = db.students.get(email);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine();

        System.out.print("Enter Grade (A/B/C/D/F): ");
        String grade = sc.nextLine().toUpperCase();

        student.addGrade(courseCode, grade);
        System.out.println("Grade successfully updated in student's record.");
    }
    
    @Override
    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        showMenu(sc);
    }

    public void showMenu(Scanner sc) {
        while (true) {
            System.out.println("\n=== PROFESSOR MENU ===");
            System.out.println("1. Manage Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Assign Grades");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int ch;
            try {
                ch = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input.");
                continue;
            }

            switch (ch) {
                case 1 -> manageCourses(sc, Database.getInstance().courseCatalog);
                case 2 -> {
                    System.out.print("Enter course code: ");
                    viewEnrolledStudents(sc.nextLine());
                }
                case 3 -> assignGrade(sc);
                case 4 -> { logout(); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}