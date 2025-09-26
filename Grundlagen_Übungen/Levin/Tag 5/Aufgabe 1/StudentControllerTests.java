package ch.tbz.m450.testing.tools;

import ch.tbz.m450.testing.tools.repository.StudentRepository;
import ch.tbz.m450.testing.tools.repository.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void save_ShouldPersistStudent() {
        // Given
        Student student = new Student("Test Student", "test@tbz.ch");

        // When
        Student savedStudent = studentRepository.save(student);

        // Then
        assertNotNull(savedStudent.getId());
        assertEquals("Test Student", savedStudent.getName());
        assertEquals("test@tbz.ch", savedStudent.getEmail());
    }

    @Test
    void findAll_ShouldReturnAllStudents() {
        // Given - Die StudentApplication fügt bereits 5 Studenten hinzu
        Student student1 = new Student("Student 1", "student1@tbz.ch");
        Student student2 = new Student("Student 2", "student2@tbz.ch");
        entityManager.persistAndFlush(student1);
        entityManager.persistAndFlush(student2);

        // When
        Iterable<Student> students = studentRepository.findAll();

        // Then - Erwarten 7 Studenten (5 von App + 2 von Test)
        assertNotNull(students);
        assertEquals(7, ((java.util.Collection<?>) students).size());
    }
}