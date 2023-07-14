package edu.kits.movie.Controller.Router;

public class Api {
    public static final String BASE = "/api/v1";
    public static class UserApi{
        public static final String USER_BASE = BASE + "/user";
        public static final String MOVIE_DETAIL = BASE + USER_BASE + "/movies/detail";
    }

    public static class AdminApi{
        public static final String USER_ADMIN = BASE + "/admin";
    }
}
