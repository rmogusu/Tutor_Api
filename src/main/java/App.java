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
    static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567;
}

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        staticFileLocation("/public");
        Sql2oTutorDao tutorDao;
        Connection conn;
        Gson gson = new Gson();


       //String connectionString = "jdbc:postgresql://localhost:5432/tutor_database";
       //Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");
        String connectionString = "jdbc:postgresql://ec2-3-228-114-251.compute-1.amazonaws.com:5432/dda5pcu9eas7qh"; //!
        Sql2o sql2o = new Sql2o(connectionString, "dmnjablysngrua", "59aae163f799502ab0b90c954134ae4305fdd092f0c90ad27d950f4fb790d712"); //!

        tutorDao = new Sql2oTutorDao(sql2o);
        conn = sql2o.open();


//        tutor
        get("/","application/json",(request, response) ->
        {
            System.out.println(tutorDao.getAll());
            if (tutorDao.getAll().size()>0)
            {
                return gson.toJson(tutorDao.getAll());
            }else
            {
                throw new ApiException(404,String.format("No Tutors in database"));
            }
        });
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
