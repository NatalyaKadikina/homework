package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepositories extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int ageMin, int ageMax);

    Collection<Student> findByFacultyId(Long id);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Long getAmountOfAllStudent();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Double getAverageOfAllStudent();

    @Query(value = "SELECT * FROM student ORDER BY id LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudent();
}

