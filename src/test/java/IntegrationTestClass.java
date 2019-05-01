import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class IntegrationTestClass {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    public void testAddAssignment1() {
        String id = "10";
        String description = "descr";
        int deadline = 7;
        int startline = 9;

        if (deadline < startline) {
            Assert.assertEquals(service.saveTema(id, description, deadline, startline), 1);
        } else {
            Assert.assertEquals(service.saveTema(id, description, deadline, startline), 0);
        }
    }

    @Test
    public void testAddGrade1() {
        Assert.assertEquals(service.saveNota("2", "3", 5, 7, "good job"), 0);
    }

    @Test
    public void testAddStudent1() {
        Assert.assertEquals(service.saveStudent("101", "Andrei", 936), 0);
    }

    @Test
    public void testAddStudentIntegration(){
        Assert.assertEquals(service.saveStudent("200", "Cristi", 936), 1);
    }

    @Test
    public void testAddAssignmentIntegration(){
        testAddStudentIntegration();
        Assert.assertEquals(service.saveTema("200", "assignCristi", 10, 7), 1);
    }

    @Test
    public void testAddGradeIntegration(){
        testAddAssignmentIntegration();
        Assert.assertEquals(service.saveNota("200", "200", 10, 10, "nothin"), 0);
    }

    @Test
    public void testAllIntegration(){
        testAddGradeIntegration();
        service.deleteStudent("200");
        service.deleteTema("200");
    }

    @Test
    public void testAll() {
        this.testAddAssignment1();
        this.testAddStudent1();
        this.testAddGrade1();
        this.testAllIntegration();
    }
}
