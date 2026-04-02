
import com.university.model.*;
import com.university.student.*;
import com.university.professor.Professor;
import com.university.admin.Administrator;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
    public static void main(String[] args) {
        Database.loadData();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to University Course Registration System ===");

        while (true) {
            System.out.println("\n1. Enter the Application");
            System.out.println("2. Enroll New Student");
            System.out.println("3. Exit the Application");
            int opt;
            try {
                opt = sc.nextInt(); sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine(); 
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
                continue;
            }

            if (opt != 1 && opt != 2 && opt != 3) {
                System.out.println("Invalid option. Please enter 1, 2, or 3.");
                continue;
            }

            if (opt == 3) {
                Database.saveData();
                System.out.println("Thank you! Goodbye.");
                break;
            }

            if (opt == 2) {
                enrollNewStudent(sc);
                continue;
            }

            System.out.println("\nLogin as:");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            System.out.println("3. Administrator");
            int role;
            try {
                role = sc.nextInt(); sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine(); 
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
                continue;
            }

            try {
                switch (role) {
                    case 1 -> studentLogin(sc);
                    case 2 -> professorLogin(sc);
                    case 3 -> adminLogin(sc);
                    default -> System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void enrollNewStudent(Scanner sc) {
        System.out.print("Student ID: "); String id = sc.nextLine();
        System.out.print("Full Name: "); String name = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        System.out.print("Semester: ");
        int semester;
        try {
            semester = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid semester. Enrollment cancelled.");
            return;
        }

        if (Database.getInstance().students.containsKey(email)) {
            System.out.println("A student with this email already exists.");
            return;
        }

        Student newStudent = new Student(id, name, email, pass, semester);
        Database.getInstance().students.put(email, newStudent);
        System.out.println("Student enrolled successfully: " + name + " (" + email + ")");
    }

    private static void studentLogin(Scanner sc) {
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();

        Student s = Database.getInstance().students.get(email);
        if (s != null && s.getPassword().equals(pass)) {
            if (s instanceof AssistantTutor tutor) {
                tutor.viewStudentGrades(s);
            }
            s.showMenu(sc);
        } else {
            System.out.println("Invalid Student credentials.");
        }
    }

    private static void professorLogin(Scanner sc) {
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();

        Professor p = Database.getInstance().professors.get(email);
        if (p != null && p.getPassword().equals(pass)) {
            p.showMenu(sc);
        } else {
            System.out.println("Invalid Professor credentials.");
        }
    }

    private static void adminLogin(Scanner sc) throws WrongPasswordException {
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();

        Administrator admin = Database.getInstance().admins.get(email);
        if (admin != null) {
            admin.authenticate(pass);
            admin.showMenu(sc);
        } else {
            System.out.println("Admin not found.");
        }
    }
}