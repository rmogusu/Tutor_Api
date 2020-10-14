package dao;

import Models.Tutor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTutorDao  implements TutorDao {
    private final Sql2o sql2o;

    public Sql2oTutorDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void saveTutor(Tutor tutor) {
        String save = "INSERT INTO tutors (name, subjects, bio, phone, experience,rating, imageUrl) VALUES (:name, :subjects, :bio, :phone, :experience, :rating, :imageUrl)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(save, true)
                    .bind(tutor)
                    .executeUpdate()
                    .getKey();
            tutor.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Tutor> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM tutors")
                    .executeAndFetch(Tutor.class);
        }
    }

    @Override
    public Tutor findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tutors WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Tutor.class);
        }
    }

    @Override
    public void update(int id, String name, String subjects, String experience, String phone, String bio, String imageUrl, String rating) {
        String update = "UPDATE tutors SET (name,subjects, experience, phone, bio, imageUrl,rating) = (:name, :subjects, :experience, :phone, :bio, :imageUrl, :rating) WHERE id=:id";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(update)
                    .addParameter("name", name)
                    .addParameter("subjects", subjects)
                    .addParameter("experience", experience)
                    .addParameter("phone", phone)
                    .addParameter("bio", bio)
                    .addParameter("imageUrl", imageUrl)
                    .addParameter("rating", rating)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

        @Override
        public void deleteById( int id){
            String sql = "DELETE from tutors WHERE id = :id";
            try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                        .addParameter("id", id)
                        .executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }

        }


    @Override
    public void clearAll() {
        String sql = "DELETE from tutors";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }


    }
}

