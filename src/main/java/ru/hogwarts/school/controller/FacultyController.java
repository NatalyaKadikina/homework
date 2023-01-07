package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;


@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;
    private final Logger logger = LoggerFactory.getLogger(FacultyController.class);

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        logger.debug("Calling method getFacultyInfo (id = {})", id);
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        logger.debug("Calling method createFaculty");
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        logger.debug("Calling method editFaculty");
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        logger.debug("Calling method deleteFaculty (id = {})", id);
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("nameOrColor")
    public ResponseEntity<Collection<Faculty>> findFacultiesNaeOrColor(@RequestParam String nameOrColor) {
        logger.debug("Calling method findFacultiesNaeOrColor (nameOrColor = {})", nameOrColor);
        return ResponseEntity.ok(facultyService.findFacultyByColorOrName(nameOrColor));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Collection<Student>> getFacultyStudents(@PathVariable Long id) {
        logger.debug("Calling method getFacultyStudents (id = {})", id);
        return ResponseEntity.ok(facultyService.getFacultyStudents(id));
    }

}
