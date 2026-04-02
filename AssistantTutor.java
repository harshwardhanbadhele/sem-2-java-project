package com.university.student;

import com.university.Feed.Feedback;

public class AssistantTutor extends Student {

	private static final long serialVersionUID = 1L;

	public AssistantTutor(String id, String name, String email, String password, int semester) {
        super(id, name, email, password, semester);
    }

    public void viewStudentGrades(Student student) {
        System.out.println("\n=== Assistant Tutor View - Student: " + student.getName() + " ===");
        new Feedback<Student>().giveFeedback(student, "Grades and courses reviewed");
    }
}