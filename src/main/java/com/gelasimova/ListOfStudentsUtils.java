package com.gelasimova;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ListOfStudentsUtils {
    private List<Student> students;

    public List<Student> getStudents(){
        return students;
    }
    //created for tests
    public void setStudents(List <Student> studentList){
        students = studentList;
    }

    //load the file
    private void initStudentsFromFile(String fileName) {
        students = new ArrayList<>();
        File file = new File(fileName);
        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (bufferedReader.ready()) {
                String inputString = bufferedReader.readLine();
                students.add(new Student(inputString));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ListOfStudentsUtils listOfStudentsUtils = new ListOfStudentsUtils();
        listOfStudentsUtils.initStudentsFromFile("C:\\Users\\Gelasimova\\Desktop\\testFile.txt");
        System.out.println("First method");
        System.out.println(listOfStudentsUtils.getThisLine(3));
        System.out.println("Second method");
        System.out.println(listOfStudentsUtils.getUniqueStudents());
        System.out.println("Third method");
        System.out.println(listOfStudentsUtils.getUniqueSortedStudents());
        System.out.println("Fourth method");
        System.out.println(listOfStudentsUtils.getUniqueStudentsOfThisYear(2));
        System.out.println("Fifth method");
        System.out.println(listOfStudentsUtils.getStudentsOfThisCourse("e2"));
        System.out.println("Sixth method");
        System.out.println(listOfStudentsUtils.addFirstStudent(new Student("Solovev,Igor,Nikolaevich,m,06.08.1991,2,a4")));
    }

    //1 
    public String getThisLine(int number) {
        Student student;
        try {
            student = students.get(number - 1);
        } catch (IndexOutOfBoundsException e) {
            student = null;
        }
        return student == null ? "There is no such line" : student.getLine();
    }

    //2 
    public Set<Student> getUniqueStudents() {
        return new LinkedHashSet<>(students);
    }

    //3 
    public Set<Student> getUniqueSortedStudents() {
        return new TreeSet<>(students);
    }

    //4 
    public Set<Student> getUniqueStudentsOfThisYear(int year) {
        Set<Student> studentSet = new HashSet<>(students);
        studentSet.removeIf(a -> a.getYear() != year);
        return studentSet;
    }

    //5 
    public List<Student> getStudentsOfThisCourse(String course) {
        if (course == null) {
            throw new NullPointerException("Incorrect argument");
        }
        List<Student> studentList = new ArrayList<>(students);
        studentList.removeIf(a -> !a.getCourse().equals(course));
        return studentList;
    }

    //6 
    public List<Student> addFirstStudent(Student newStudent) {
        if(newStudent == null){
            throw new NullPointerException("Incorrect argument");
        }
        LinkedList<Student> studentLinkedList = new LinkedList<>(students);
        studentLinkedList.addFirst(newStudent);
        return studentLinkedList;
    }
}

class Student implements Comparable {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String lastName;
    private String firstName;
    private String middleName;
    private boolean sex;
    private LocalDate birthday;
    private int year;
    private String course;
    private String fullName;

    public int getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getLine() {
        return lastName + " " + firstName + " " + middleName + " " + sex + " " + birthday.format(formatter) + " " + year + " " + course;
    }

    public Student(String inputString) {
        String[] data = inputString.split(",");
        lastName = data[0];
        firstName = data[1];
        middleName = data[2];
        sex = data[3].equals("m");
        birthday = LocalDate.parse(data[4], formatter);
        year = Integer.parseInt(data[5]);
        course = data[6];
        fullName = lastName + firstName + middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Student obj = (Student) o;
        return this.fullName.equals(obj.fullName) && this.birthday.equals(obj.birthday);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 2 * hash + fullName.hashCode();
        hash = 2 * hash + birthday.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName + " " + birthday.format(formatter);
    }

    @Override
    public int compareTo(Object o) {
        Student obj = (Student) o;
        int value = fullName.compareTo(obj.fullName);
        if (value == 0) {
            value = birthday.compareTo(obj.birthday);
        }
        return value;
    }
}
