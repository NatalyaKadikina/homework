package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface  FacultyRepositories extends JpaRepository<Faculty, Long> {
    Collection<Faculty>  findFacultyByColorIgnoreCaseOrNameIgnoreCase(String name, String color);
    public Collection<Faculty> findByColor(String color);
}



