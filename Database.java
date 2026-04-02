package com.university.model;

import com.university.student.Student;
import com.university.student.AssistantTutor;
import com.university.professor.Professor;
import com.university.admin.Administrator;
import java.io.*;
import java.util.*;

public class Database implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Database instance;

    public Map<String, Student> students = new HashMap<>();
    public Map<String, Professor> professors = new HashMap<>();
    public Map<String, Administrator> admins = new HashMap<>();
    public List<Course> courseCatalog = new ArrayList<>();

    private Database() {
       
        courseCatalog.add(new Course("CS101", "Introduction to Programming", 4, "Mon 9-11AM", "A101", "None"));
        courseCatalog.get(0).setProfessor("Dr. Sharma");
        courseCatalog.add(new Course("MATH101", "Calculus I", 2, "Tue 10AM", "B201", "None"));
        courseCatalog.get(1).setProfessor("TBA");

        Administrator admin = new Administrator("ADM01", "System Admin", "admin@uni.edu");
        admins.put(admin.getEmail(), admin);

        Student s1 = new Student("S001", "Harsh Student", "student@uni.edu", "pass123", 1);
        students.put(s1.getEmail(), s1);

        Professor p1 = new Professor("P001", "Dr. Sharma", "prof@uni.edu", "prof123");
        professors.put(p1.getEmail(), p1);

        AssistantTutor tutor = new AssistantTutor("T001", "Assistant Tutor Raj", "tutor@uni.edu", "tutor123", 1);
        students.put(tutor.getEmail(), tutor);
    }

    public static Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("university_data.ser"))) {
            oos.writeObject(getInstance());
            System.out.println("All changes saved successfully.");
        } catch (IOException e) {
            System.out.println("Warning: Could not save data.");
        }
    }

    public static void loadData() {
        File f = new File("university_data.ser");
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                instance = (Database) ois.readObject();
                System.out.println("Previous data loaded.");
            } catch (Exception ignored) {}
        }
    }

	
}