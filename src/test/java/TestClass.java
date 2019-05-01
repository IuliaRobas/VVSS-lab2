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
        String id = "100";
        String description = "descr";
        int deadline = 7;
        int startline = 9;

        if (deadline<startline){
            Assert.assertEquals(service.saveTema(id, description, deadline, startline), 1);
        }
        else{
            Assert.assertEquals(service.saveTema(id, description, deadline, startline), 0);
        }
    }

    @Test
    public void testAddAssignment2() {
        String id = "100";
        String description = "descr";
        int deadline = 9;
        int startline = 7;

        if (service.getTemaXmlRepo().findOne(id)==null){
            Assert.assertEquals(service.saveTema(id, description, deadline, startline), 1);
        }
        else{
            Assert.assertEquals(service.saveTema(id, description, deadline, startline), 0);
        }
    }

    @Test
    public void testAddAssignment3(){
        String id="0";
        String description = "descr";
        int deadline = 9;
        int startline = 7;

        if (service.getTemaXmlRepo().findOne(id)==null){
            Assert.assertEquals(service.saveTema(id, description, deadline, startline), 1);
            service.deleteTema("0");

        }


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
