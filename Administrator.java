package com.university.admin;

import java.util.Map.Entry;
import java.util.Scanner;
import com.university.model.*;
import com.university.professor.Professor;
import com.university.student.Student;

import java.io.Serializable;

public class Administrator extends Course implements UserRole,Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String fixedPassword = "admin123";

    public Administrator(String id, String name, String email) {
        super(id, name, email, "admin123");
    }

    public void authenticate(String pass) throws WrongPasswordException {
        if (!fixedPassword.equals(pass)) {
            throw new WrongPasswordException("Incorrect Administrator Password!");
        }
    }
    public void handleComplaints(Scanner sc) {
        Database db = Database.getInstance();

        System.out.println("\n=== Pending Student Complaints ===");

        boolean hasPending = false;

        for (Student student : db.students.values()) {
            for (Entry<String, String> entry : student.getComplaints().entrySet()) {
                if ("Pending".equals(entry.getValue())) {
                    hasPending = true;

                    System.out.println("\nStudent     : " + student.getName());
                    System.out.println("Email       : " + student.getEmail());
                    System.out.println("Complaint   : " + entry.getKey());
                    System.out.println("Status      : " + entry.getValue());

                    System.out.print("\nEnter resolution for this complaint: ");
                    String resolution = sc.nextLine();

                    student.getComplaints().put(entry.getKey(), "Resolved");

                    System.out.println("Complaint resolved successfully.");
                    System.out.println("Resolution : " + resolution);
                    System.out.println("-----------------------------------");
                }
            }
        }

        if (!hasPending) {
            System.out.println("No pending complaints at the moment.");
        }
    }
    private void manageCourseCatalog(Scanner sc) {
        Database db = Database.getInstance();

        while (true) {
            System.out.println("\n=== Manage Course Catalog ===");
            System.out.println("1. View All Courses");
            System.out.println("2. Add New Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) 
            {
                case 1 -> {
                    System.out.println("\n--- All Courses ---");
                    for (Course c : db.courseCatalog) 
                    {
                        System.out.println(c.getCourseCode() + " | " + c.getTitle() 
                                           + " | Credits: " + c.getCredits() 
                                           + " | Timings: " + c.getTimings());
                    }
                }

                case 2 -> {
                    System.out.print("Enter Course Code: ");
                    String code = sc.nextLine();
                    System.out.print("Enter Course Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Credits (2 or 4): ");
                    int credits;
                    try {
                        credits = sc.nextInt(); sc.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("Invalid credits value.");
                        break;
                    }
                    db.courseCatalog.add(new Course(code, title, credits, "TBD", "TBD", "None"));
                    System.out.println("New course added successfully!");
                }

                case 3 -> {
                    System.out.print("Enter Course Code to delete: ");
                    String code = sc.nextLine();
                    boolean removed = db.courseCatalog.removeIf(c -> c.getCourseCode().equals(code));
                    System.out.println(removed ? "Course " + code + " deleted." : "Course not found!");
                }

                case 4 -> { return; }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
    public void assignProfessorToCourse(Scanner sc) {
        Database db = Database.getInstance();

        System.out.println("\n=== Assign Professor to Course ===");

        System.out.println("Available Courses:");
        for (Course c : db.courseCatalog) {
            System.out.println(c.getCourseCode() + " - " + c.getTitle());
        }

        System.out.print("\nEnter Course Code: ");
        String courseCode = sc.nextLine().trim();

        System.out.println("\nAvailable Professors:");
        for (Professor p : db.professors.values()) {
            System.out.println(p.getId() + " - " + p.getName());
        }

        System.out.print("Enter Professor ID: ");
        String profId = sc.nextLine().trim();

        Professor professor = null;
        for (Professor p : db.professors.values()) {
            if (p.getId().equals(profId)) {
                professor = p;
                break;
            }
        }

        if (professor == null) {
            System.out.println("Professor not found!");
            return;
        }

        boolean courseExists = db.courseCatalog.stream()
                .anyMatch(c -> c.getCourseCode().equals(courseCode));

        if (!courseExists) {
            System.out.println("Course " + courseCode + " not found!");
            return;
        }

        professor.assignCourse(courseCode);
    }
    public void manageStudentRecords() {
        System.out.println("\n=== Student Records ===");
        for (Student s : Database.getInstance().students.values()) {
            System.out.println(s.getId() + " - " + s.getName() + " | Semester: " + s.getCurrentSemester());
        }
    }
    @Override
    public void showMenu() {
        showMenu(new Scanner(System.in));
    }

    public void showMenu(Scanner sc) {
        while (true) {
            System.out.println("\n=== ADMINISTRATOR MENU ===");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. Assign Professor to Course");
            System.out.println("4. Handle Complaints");
            System.out.println("5. Logout");
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
                case 1 -> manageCourseCatalog(sc);
                case 2 -> manageStudentRecords();
                case 3 -> assignProfessorToCourse(sc);
                case 4 -> handleComplaints(sc);
                case 5 -> { logout(); return; }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
