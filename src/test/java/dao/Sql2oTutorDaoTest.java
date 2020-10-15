package dao;

import Models.Tutor;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.FileOutputStream;
import java.io.Writer;

import static org.junit.Assert.*;

public class Sql2oTutorDaoTest {

    private static Connection conn;
    private static  Sql2oTutorDao tutorDao;
    @BeforeClass
    public static void setUp() throws Exception {
        //String connectionString = "jdbc:postgresql://localhost:5432/tutor_test_database";
        //Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");
        String connectionString = "jdbc:postgresql://@ec2-3-224-97-209.compute-1.amazonaws.com:5432/dflot1usec0jea"; //!
        Sql2o sql2o = new Sql2o(connectionString, "clspsryyyvediz", "5387ed687b2231864ae6ea025770221a638d97ccc9477218184e8254f7ded2ba"); //!
        System.out.println("Connection Initialized");
        tutorDao = new Sql2oTutorDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clear Database");
        tutorDao.clearAll();
        
    }

    @AfterClass
    public static void shutDown()throws Exception
    {
        conn.close();
        System.out.println("Connection closed");
    }


    @Test
    public void save_assignsIdToObject() throws Exception{
        Tutor testTutor = setupTutor();
        tutorDao.saveTutor(testTutor ); ;
        Tutor savedTutor= tutorDao .getAll().get(0);
        assertEquals(testTutor.getId(), savedTutor.getId());
    }
    @Test
    public void addedTutorsAreReturnedFromGetAll() throws Exception {
        Tutor testTutor = setupTutor();
        tutorDao.saveTutor(testTutor);
        Tutor otherTutor = setupTutor();
        tutorDao.saveTutor(otherTutor);
        assertEquals(2, tutorDao.getAll().size());
    }

    @Test
    public void noTutorReturnsEmptyList() throws Exception {
        assertEquals(0,tutorDao.getAll().size());
    }
    @Test
    public void findByIdReturnsCorrectTutor() throws Exception {
        Tutor testTutor= setupTutor();
        tutorDao.saveTutor(testTutor);
        Tutor  foundTutor = tutorDao.findById(testTutor .getId());
        assertEquals(testTutor , foundTutor );

    }

    @Test
    public void update()throws Exception {
        Tutor testTutor= setupTutor();
        tutorDao.saveTutor(testTutor); ;
        tutorDao.update(testTutor.getId(),"Faith","English","4 years","0723009659","cool","ruth.jpg","6.6");
        Tutor  getTutor = tutorDao.findById(testTutor .getId());
        assertEquals(getTutor .getId(),testTutor.getId());
        assertNotEquals(getTutor .getName(),testTutor .getName());
        assertNotEquals(getTutor .getSubjects(),testTutor .getSubjects());
        assertNotEquals(getTutor .getExperience(),testTutor .getExperience());
        assertNotEquals(getTutor .getPhone() ,testTutor .getPhone() );
        assertNotEquals(getTutor .getBio() ,testTutor.getBio());
        assertNotEquals(getTutor .getImageUrl(),testTutor .getImageUrl());
        assertNotEquals(getTutor .getRating(),testTutor .getRating());


    }
    @Test
    public void deleteByIdDeletesCorrectTutor() throws Exception {
        Tutor testTutor= setupTutor();
        tutorDao.saveTutor(testTutor);
        Tutor otherTutor = setupTutor();
        tutorDao.saveTutor(otherTutor);
        tutorDao.deleteById(otherTutor.getId());
        assertEquals(1, tutorDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Tutor testTutor= setupTutor();
        tutorDao.saveTutor(testTutor);
        Tutor otherTutor = setupTutor();
        tutorDao.saveTutor(otherTutor);
        tutorDao.clearAll();
        assertEquals(0, tutorDao.getAll().size());
    }

    //helpers
    public Tutor setupTutor() {
        return new Tutor("Rose", "5 years", "rose.jpg", "4.5","0715350171","Maths","Me");

    }
}