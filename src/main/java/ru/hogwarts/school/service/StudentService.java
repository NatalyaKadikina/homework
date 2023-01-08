package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepositories studentRepositories;
    private int countSync = 0;
    public StudentService(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    public Student addStudent(Student student) {
        return studentRepositories.save(student);
    }

    public Student findStudent(long id) {
        return studentRepositories.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        if (studentRepositories.findById(student.getId()).orElse(null) == null) {
            return null;
        }
        return studentRepositories.save(student);
    }

    public void deleteStudent(long id) {
        studentRepositories.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentRepositories.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
        return studentRepositories.findByAgeBetween(ageMin, ageMax);
    }

    public Faculty  getStudentFaculty(long id) {
        Student student = findStudent(id);
        if (student == null) {
            return null;
        }
        return student.getFaculty();
    }

    public Long getAmountOfAllStudent() {
        return studentRepositories.getAmountOfAllStudent();
    }

    public double getAverageOfAllStudent() {
        return studentRepositories.getAverageOfAllStudent();
    }

    public List<Student> getLastStudentsById() {
        return studentRepositories.getLastFiveStudent();
    }

    public Collection<String> getFilteredByName() {
        return studentRepositories.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }


    public Double getAllStudentsAvgAge() {
        return studentRepositories.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    public Collection<Student> getAll() {
        return studentRepositories.findAll();
    }

    public void getAllStudentNames() {
        List<String> names = studentRepositories.findAll().stream()
                .map(user -> user.getName())
                .collect(Collectors.toList());

        printName(names.get(0));
        printName(names.get(1));

        new Thread(() -> {
            printName(names.get(2));
            printName(names.get(3));
        }).start();

        new Thread(() -> {
            printName(names.get(4));
            printName(names.get(5));
        }).start();

    }

    public void getAllStudentNamesAsync() {
        List<String> names = studentRepositories.findAll().stream()
                .map(user -> user.getName())
                .collect(Collectors.toList());

        printNameAsync(names);
        printNameAsync(names);

        new Thread(() -> {
            printNameAsync(names);
            printNameAsync(names);
        }).start();

        new Thread(() -> {
            printNameAsync(names);
            printNameAsync(names);
        }).start();
    }

    private void printName(String str) {
        System.out.println(str);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized void printNameAsync(List<String> names) {
        System.out.println(names.get(countSync));
        countSync++;
    }

}
