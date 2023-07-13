package edu.kits.movie.Service;

import edu.kits.movie.Dto.Response.UserResponse;

public interface UserService {
    String getCurrentUser();
    UserResponse getUserInfo();
}
