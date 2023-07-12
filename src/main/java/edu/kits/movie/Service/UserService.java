package edu.kits.movie.Service;

import edu.kits.movie.Model.Response.UserResponse;

public interface UserService {
    String getCurrentUser();
    UserResponse getUserInfo();
}
