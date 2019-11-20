package com.gelasimova;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.fail;

public class ListOfStudentsUtilsTest {
    private ListOfStudentsUtils listOfStudentsUtils;

    @Before
    public void setUp() {
        listOfStudentsUtils = new ListOfStudentsUtils();
        listOfStudentsUtils.students = new ArrayList<>();
        listOfStudentsUtils.students.add(new Student("Gelasimova,Lera,Andreevna,f,06.11.1992,5,r1"));
        listOfStudentsUtils.students.add(new Student("Ivanov,Artem,Ivanovich,m,05.12.1990,4,e2"));
        listOfStudentsUtils.students.add(new Student("Ivanov,Artem,Ivanovich,m,05.12.1990,4,m1"));
        listOfStudentsUtils.students.add(new Student("Solovev,Oleg,Glebovich,m,07.06.1989,5,e2"));
        listOfStudentsUtils.students.add(new Student("Solovev,Oleg,Glebovich,m,05.06.1989,5,a4"));
    }

    @Test
    public void zeroArgumentM1() {
        Assert.assertEquals("There is no such line", listOfStudentsUtils.getThisLine(0));
    }

    @Test
    public void negativeArgumentM1() {
        Assert.assertEquals("There is no such line", listOfStudentsUtils.getThisLine(-1));
    }

    @Test
    public void correctArgumentM1() {
        Assert.assertEquals(listOfStudentsUtils.students.get(2).getLine(), listOfStudentsUtils.getThisLine(3));
    }

    @Test
    public void areTheElementsUniqueM2() {
        Set<Student> testSet = listOfStudentsUtils.getUniqueStudents();
        int count = 0;
        for (Student s : testSet) {
            for (Student s1 : testSet) {
                if (s.equals(s1)) {
                    count++;
                }
            }
            Assert.assertEquals(1, count);
            count = 0;
        }
    }

    @Test
    public void isTheOrderCorrectM2() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getUniqueStudents());
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(listOfStudentsUtils.students.get(0));
        expected.add(listOfStudentsUtils.students.get(1));
        expected.add(listOfStudentsUtils.students.get(3));
        expected.add(listOfStudentsUtils.students.get(4));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void areTheElementsUniqueM3() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getUniqueSortedStudents());
        Assert.assertEquals(4, actual.size());
        Assert.assertTrue(actual.contains(listOfStudentsUtils.students.get(0)));
        Assert.assertTrue(actual.contains(listOfStudentsUtils.students.get(1)));
        Assert.assertTrue(actual.contains(listOfStudentsUtils.students.get(3)));
        Assert.assertTrue(actual.contains(listOfStudentsUtils.students.get(4)));
    }

    @Test
    public void isTheOrderCorrectM3() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getUniqueSortedStudents());
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(listOfStudentsUtils.students.get(0));
        expected.add(listOfStudentsUtils.students.get(1));
        expected.add(listOfStudentsUtils.students.get(4));
        expected.add(listOfStudentsUtils.students.get(3));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void areTheElementsUniqueM4() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getUniqueStudentsOfThisYear(4));
        Assert.assertEquals(1, actual.size());
        Assert.assertTrue(actual.contains(listOfStudentsUtils.students.get(1)));
    }

    @Test
    public void zeroArgumentM4() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getUniqueStudentsOfThisYear(0));
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void isTheYearCorrectM4() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getUniqueStudentsOfThisYear(4));
        for (Student student : actual) {
            Assert.assertEquals(4, student.getYear());
        }
    }

    @Test
    public void nullArgumentM5() {
        try {
            ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getStudentsOfThisCourse(null));
            fail();
        } catch (NullPointerException e) {
            //pass
        }
    }

    @Test
    public void nonexistentArgumentM5() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getStudentsOfThisCourse(""));
        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void isTheCourseCorrectM5() {
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.getStudentsOfThisCourse("e2"));
        for (Student student: actual) {
            Assert.assertEquals("e2", student.getCourse());
        }
    }

    @Test
    public void nullArgumentM6() {
        try {
            ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.addFirstStudent(null));
            fail();
        } catch (NullPointerException e) {
            //pass
        }
    }

    @Test
    public void aStudentWasAddedM6(){
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.addFirstStudent(new Student("Gelasimova,Lera,Andreevna,f,11.11.1992,5,r1")));
        Assert.assertEquals(6,actual.size());
    }

    @Test
    public void firstStudentWasAddedM6(){
        Student newStudent = new Student("Gelasimova,Lera,Andreevna,f,11.11.1992,5,r1");
        ArrayList<Student> actual = new ArrayList<>(listOfStudentsUtils.addFirstStudent(newStudent));
        Assert.assertEquals(newStudent,actual.get(0));
    }
}
