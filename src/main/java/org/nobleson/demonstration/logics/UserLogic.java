package org.nobleson.demonstration.logics;

import lombok.RequiredArgsConstructor;
import org.nobleson.demonstration.models.AppUser;
import org.nobleson.demonstration.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLogic implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser addUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser updateUser(AppUser user) {

        AppUser user1 = userRepository.getAppUserByUserID(user.getUserID()).orElseThrow();

        return userRepository.save(user1);
    }

    @Override
    public void deleteUser(String userID) {
        AppUser user = userRepository.getAppUserByUserID(userID).orElseThrow();

        userRepository.delete(user);
    }

    @Override
    public AppUser getUserByID(String userID) {
        return userRepository.getAppUserByUserID(userID).orElseThrow();
    }
}
