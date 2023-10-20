package br.com.gomes.manager.Enums;

import java.awt.font.TextHitInfo;

public enum Course {

    NONE("Select"),
    COMPUTER_SCIENCE("Computer Science"),
    ELECTRICAL_ENGINEERING("Electrical Engineering"),
    MECHANICAL_ENGINEERING("Mechanical Engineering"),
    CIVIL_ENGINEERING("Civil Engineering"),
    BUSINESS_ADMINISTRATION("Business Administration"),
    ECONOMICS("Economics"),
    PSYCHOLOGY("Psychology"),
    BIOLOGY("Biology"),
    CHEMISTRY("Chemistry"),
    PHYSICS("Physics"),
    MATHEMATICS("Mathematics"),
    ENVIRONMENTAL_SCIENCE("Environmental Science"),
    POLITICAL_SCIENCE("Political Science"),
    HISTORY("History"),
    SOCIOLOGY("Sociology"),
    ENGLISH_LITERATURE("English Literature"),
    COMMUNICATIONS("Communications"),
    NURSING("Nursing"),
    MEDICINE("Medicine"),
    LAW("Law");

    private final String courseName;

    Course(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }
}
