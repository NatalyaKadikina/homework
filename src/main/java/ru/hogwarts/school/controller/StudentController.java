package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        logger.debug("Calling method getStudentInfo (id = {})", id);
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        logger.debug("Calling method createStudent");
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        logger.debug("Calling method editStudent");
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        logger.debug("Calling method deleteStudent (id = {})", id);
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/age")
    public ResponseEntity<Collection<Student>>findStudents(@RequestParam Integer age) {
        logger.debug("Calling method findStudents (age = {})", age);
        return ResponseEntity.ok(studentService.findByAge(age));
    }

    @GetMapping("/age/between")
    public ResponseEntity<Collection<Student>>  findStudentByAge(@RequestParam Integer  ageMin, @RequestParam Integer ageMax) {
        logger.debug("Calling method findStudentByAge (minAge = {}, maxAge = {})", ageMin, ageMax);
        return ResponseEntity.ok(studentService.findByAgeBetween(ageMin, ageMax));
    }
    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        logger.debug("Calling method getStudentFaculty (id = {})", id);
        return ResponseEntity.ok(studentService.getStudentFaculty(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getAmountOfAllStudent() {
        logger.debug("Calling method getAmountOfAllStudent");
        return ResponseEntity.ok(studentService.getAmountOfAllStudent());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageAge() {
        logger.debug("Calling method getAverageAge");
        return ResponseEntity.ok(studentService.getAverageOfAllStudent());
    }

    @GetMapping("/last-students")
    public ResponseEntity<List<Student>> getLastStudentsById(@RequestParam Integer limit) {
        logger.debug("Calling method getStudentFaculty (limit = {})", limit);
        return ResponseEntity.ok(studentService.getLastStudentsById());
    }

    @GetMapping("/filteradbyname")
    public ResponseEntity<Collection<String>> getAllStudentsWithAName() {
        Collection<String> stringCollection = studentService.getFilteredByName();
        if (stringCollection.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(stringCollection);
    }

    @GetMapping("/getallstudentsavgagewithstream")
    public Double getAllStudentsAvgAgeWithStream() {
        return studentService.getAllStudentsAvgAge();
    }

    @GetMapping("/all-students")
    public void getAllStudentNames () {
        studentService.getAllStudentNames();
    }

    @GetMapping("/all-students-async")
    public void getAllStudentNamesAsync () {
        studentService.getAllStudentNamesAsync();
    }
}
