package org.nobleson.demonstration.logics;

import org.nobleson.demonstration.models.AppUser;

import java.util.List;

public interface UserService {

    List<AppUser> getAllUsers();
    AppUser addUser(AppUser user);
    AppUser updateUser(AppUser user);
    void deleteUser(String userID);
    AppUser getUserByID(String userID);
}
