import Models.Tutor;
import com.google.gson.Gson;
import dao.Sql2oTutorDao;
import exceptions.ApiException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oTutorDao tutorDao;
        Connection conn;
        Gson gson = new Gson();


       String connectionString = "jdbc:postgresql://localhost:5432/tutor_database";
       Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");

        //String connectionString = "jdbc:postgresql://ec2-34-200-72-77.compute-1.amazonaws.com:5432/d76hbp26t1gp7g";
        //Sql2o sql2o = new Sql2o(connectionString, "xheugcjqyscryb", "9d62b7aa1616cdf49154abc03f568434554ea902e33f44a26924dedfc5c0eae2");


        tutorDao = new Sql2oTutorDao(sql2o);
        conn = sql2o.open();


//        tutor

        post("/tutors/new", "application/json", (req, res) -> {
            Tutor tutor = gson.fromJson(req.body(), Tutor.class);
            tutorDao.saveTutor(tutor);
            res.status(201);;
            return gson.toJson(tutor);
        });

        get("/tutors", "application/json", (req, res) -> {

            //return gson.toJson(tutorDao.getAll());

            System.out.println(tutorDao.getAll());
            if (tutorDao.getAll().size()>0)
            {
                return gson.toJson(tutorDao.getAll());
            }else
            {
                throw new ApiException(404,String.format("No Tutors in  database"));
            }
        });

        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });
        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });
    }
}
