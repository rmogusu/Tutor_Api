package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TutorTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewTutorObjectGetsCorrectlyCreated_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals(true, tutor  instanceof Tutor );
    }
    @Test
    public void Tutor_InstantiatesWithName_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals("Rose", tutor .getName());
    }


    @Test
    public void Tutor_SetsNameCorrectly_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        tutor .setName("Faith"); ;
        assertNotEquals("Rose", tutor.getName());
    }
    @Test
    public void Tutor_InstantiatesWithExperience_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals("5 years", tutor .getExperience());
    }

    @Test
    public void Tutor_SetsExperienceCorrectly_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        tutor.setExperience("4 years"); ;
        assertNotEquals("5 years", tutor.getExperience());
    }

    @Test
    public void Tutor_InstantiatesWithRating_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals("4.5", tutor .getRating());
    }

    @Test
    public void Tutor_SetsRatingCorrectly_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        tutor.setRating("4.0"); ;
        assertNotEquals("4.5", tutor.getRating());
    }

    @Test
    public void Tutor_InstantiatesWithPhone_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals("0715350171", tutor .getPhone());
    }

    @Test
    public void Tutor_SetsPhoneCorrectly_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        tutor.setPhone("0723009659"); ;
        assertNotEquals("0715350171", tutor.getPhone());
    }

    @Test
    public void Tutor_InstantiatesWithSubjet_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals("Maths", tutor .getSubjects());
    }

    @Test
    public void Tutor_SetsSubjetCorrectly_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        tutor.setSubjects("English"); ;
        assertNotEquals("Maths", tutor.getSubjects());
    }

    @Test
    public void Tutor_InstantiatesWithBio_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals("Me", tutor .getBio());
    }

    @Test
    public void Tutor_SetsBioCorrectly_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        tutor.setBio("you"); ;
        assertNotEquals("Me", tutor.getBio());
    }

    @Test
    public void Tutor_InstantiatesWithImageUrl_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        assertEquals("rose.jpg", tutor .getImageUrl());
    }

    @Test
    public void Tutor_SetsImageUrlCorrectly_true() throws Exception {
        Tutor tutor  = setupNewTutor();
        tutor.setImageUrl("ruth.jpg"); ;
        assertNotEquals("rose.jpg", tutor.getImageUrl());
    }



    //helper methods
    public Tutor setupNewTutor(){
        return new Tutor("Rose", "5 years", "rose.jpg", "4.5","0715350171","Maths","Me");
    }

}