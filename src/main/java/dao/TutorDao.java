package dao;

import Models.Tutor;

import java.util.List;

public interface TutorDao {

     //    CREATE
    void saveTutor(Tutor tutor);

    //    READ
    List<Tutor> getAll();
    Tutor findById(int id);

    //    UPDATE
    void update(int id, String name, String subjects, String experience, String phone, String bio, String imageUrl, String rating);

    //    DESTROY
    void clearAll();
    void deleteById(int id);

}
