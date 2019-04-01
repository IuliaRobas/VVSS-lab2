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

public class TestClass {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    public void testAddAssignment1() {
        Assert.assertEquals(1, 1);
    }

    /*Test case correct input for all fields*/
    @Test
    public void testAddStudent1() {
        Assert.assertEquals(service.saveStudent("101", "Andrei", 936), 0);
    }

    /*Test case for invalid id*/
    @Test
    public void testAddStudent2() {
        Assert.assertEquals(service.saveStudent("-1", "Andrei", 936), 0);
    }

    /*Test case for invalid name*/
    @Test
    public void testAddStudent3() {
        Assert.assertEquals(service.saveStudent("105", "Andrei12", 936), 0);
    }

    /*Test case for invalid name*/
    @Test
    public void testAddStudent4() {
        Assert.assertEquals(service.saveStudent("105", "", 936), 1);
    }

    /*Test case for invalid group*/
    @Test
    public void testAddStudent5() {
        Assert.assertEquals(service.saveStudent("105", "Andrei", -1), 1);
    }

    /*Test case for invalid group*/
    @Test
    public void testAddStudent6() {
        Assert.assertEquals(service.saveStudent("105", "Andrei", Integer.MAX_VALUE + 1), 1);
    }

}
