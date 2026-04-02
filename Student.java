package com.university.student;

import com.university.model.*;
import java.io.Serializable;
import java.util.*;

public class Student extends Course implements UserRole, Serializable {

    private static final long serialVersionUID = 1L;

    private int currentSemester = 1;
    private List<String> enrolledCourses = new ArrayList<>();
    private Map<String, String> grades = new HashMap<>();

    // Complaint storage using Map: Key = Complaint, Value = Status
    private Map<String, String> complaints = new HashMap<>();

    public Student(String id, String name, String email, String password, int semester) {
        super(id, name, email, password);
        this.currentSemester = semester;
    }

    // ==================== GETTERS ====================
    public Map<String, String> getComplaints() {
        return complaints;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    // ==================== GRADE METHOD (Added as requested) ====================
    /**
     * This method is called by Professor to assign/update grade
     */
    public void addGrade(String courseCode, String grade) {
        if (courseCode == null || grade == null) {
            System.out.println("Invalid course code or grade!");
            return;
        }
        grades.put(courseCode.trim(), grade.toUpperCase());
        System.out.println("Grade " + grade.toUpperCase() + " assigned for course " + courseCode);
    }

    // ==================== COMPLAINT METHODS ====================
    public void submitComplaint(String description) {
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Complaint cannot be empty!");
            return;
        }

        String trimmedDesc = description.trim();
        if (complaints.containsKey(trimmedDesc)) {
            System.out.println("This complaint has already been submitted.");
        } else {
            complaints.put(trimmedDesc, "Pending");
            System.out.println("Complaint submitted successfully. Status: Pending");
        }
    }

    public void viewComplaints() {
        System.out.println("\n=== Your Complaints ===");
        if (complaints.isEmpty()) {
            System.out.println("No complaints submitted yet.");
            return;
        }

        int i = 1;
        for (Map.Entry<String, String> entry : complaints.entrySet()) {
            System.out.println(i + ". " + entry.getKey() + " → " + entry.getValue());
            i++;
        }
    }

    // ==================== OTHER METHODS ====================
    public void viewAvailableCourses() {
        System.out.println("\n=== Available Courses for Semester " + currentSemester + " ===");
        for (Course c : Database.getInstance().courseCatalog) {
            System.out.println(c.getCourseCode() + " - " + c.getTitle() 
                               + " (" + c.getCredits() + " credits)");
        }
    }

    public void registerCourse(String courseCode) 
            throws CourseFullException, PrerequisiteNotMetException, AlreadyEnrolledException {
        
        if (enrolledCourses.contains(courseCode)) {
            throw new AlreadyEnrolledException("You are already enrolled in this course!");
        }

        Course course = null;
        for (Course c : Database.getInstance().courseCatalog) {
            if (c.getCourseCode() != null && c.getCourseCode().equals(courseCode)) {
                course = c;
                break;
            }
        }

        if (course == null) throw new PrerequisiteNotMetException("Course not found!");

        if (course.getEnrollmentLimit() <= 0) {
            throw new CourseFullException("Course is full! Cannot register.");
        }

        enrolledCourses.add(courseCode);
        course.setEnrollmentLimit(course.getEnrollmentLimit() - 1);
        System.out.println("Successfully registered for " + courseCode);
    }

    public void viewSchedule() {
        System.out.println("\n=== Your Current Schedule ===");
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled yet.");
            return;
        }
        for (String code : enrolledCourses) {
            Course course = null;
            for (Course c : Database.getInstance().courseCatalog) {
                if (c.getCourseCode() != null && c.getCourseCode().equals(code)) {
                    course = c;
                    break;
                }
            }
            if (course != null) {
                System.out.println("- " + course.getCourseCode() + " | " + course.getTitle()
                        + " | Timing: " + course.getTimings()
                        + " | Location: " + course.getLocation()
                        + " | Professor: " + course.getProfessor());
            } else {
                System.out.println("- " + code + " (details not found)");
            }
        }
    }

    public void trackAcademicProgress() {
        System.out.println("\n=== Academic Progress (Semester " + currentSemester + ") ===");
        System.out.println("Grades: " + grades);
        if (grades.isEmpty()) {
            System.out.println("No grades assigned yet.");
        }
    }

    public void dropCourse(String courseCode) throws DropDeadlineExceededException {
        if (!enrolledCourses.contains(courseCode)) {
            System.out.println("You are not enrolled in this course.");
            return;
        }
        throw new DropDeadlineExceededException("Drop deadline has passed. Cannot drop course now.");
    }
    
    // ==================== MENU ====================
    @Override
    public void showMenu() {
        showMenu(new Scanner(System.in));
    }

    public void showMenu(Scanner sc) {
        while (true) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. View Schedule");
            System.out.println("4. Track Academic Progress");
            System.out.println("5. Drop Course");
            System.out.println("6. Submit Complaint");
            System.out.println("7. View My Complaints");
            System.out.println("8. Logout");
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

            try {
                switch (choice) {
                    case 1 -> viewAvailableCourses();
                    case 2 -> {
                        System.out.print("Enter Course Code: ");
                        registerCourse(sc.nextLine());
                    }
                    case 3 -> viewSchedule();
                    case 4 -> trackAcademicProgress();
                    case 5 -> {
                        System.out.print("Enter Course Code to drop: ");
                        dropCourse(sc.nextLine());
                    }
                    case 6 -> {
                        System.out.print("Enter complaint description: ");
                        submitComplaint(sc.nextLine());
                    }
                    case 7 -> viewComplaints();
                    case 8 -> {
                        logout();
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}                   