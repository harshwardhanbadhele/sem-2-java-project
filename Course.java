package com.university.model;

import java.io.Serializable;

public class Course implements UserRole, Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String name;
    protected String email;
    protected String password;

    protected String courseCode;
    protected String title;
    protected int credits;
    protected String timings;
    protected String location;
    protected String prerequisites;
    protected int enrollmentLimit = 30;
    protected String professor;

    

    public Course(String courseCode, String title, int credits, String timings, 
                  String location, String prerequisites) {
        this.id = courseCode;
        this.name = title;
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.timings = timings;
        this.location = location;
        this.prerequisites = prerequisites;
    }

    public Course(String id, String name, String email, String password) {
    	this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;	
    }

	public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getTimings() { return timings; }
    public String getLocation() { return location; }
    public String getPrerequisites() { return prerequisites != null ? prerequisites : "None"; }
    public int getEnrollmentLimit() { return enrollmentLimit; }

    public void setEnrollmentLimit(int limit) { this.enrollmentLimit = limit; }
    public void setTimings(String timings) { this.timings = timings; }
    public String getProfessor() { return professor != null ? professor : "TBA"; }
    public void setProfessor(String professor) { this.professor = professor; }

    @Override
    public void showMenu() {
        System.out.println("=== Base Course Menu ===");
    }

    @Override
    public void logout() {
        System.out.println("Logged out successfully. Changes saved.");
    }
    
    public String getDetails() {
        return "Code: " + courseCode + " | Title: " + title + " | Credits: " + credits +
               " | Timing: " + timings + " | Location: " + location;
    }
    
}