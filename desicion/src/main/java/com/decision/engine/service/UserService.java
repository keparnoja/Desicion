package com.decision.engine.service;


import com.decision.engine.Model.User;
import com.decision.engine.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){

        if (user.getCreditModifier()!= 0 && user.getCreditModifier() >=100 && user.getCreditModifier() <200){
            user.setCreditModifier(100);
        }else if (user.getCreditModifier()!= 0 && user.getCreditModifier() >=200 && user.getCreditModifier() <300){
            user.setCreditModifier(200);
        } else if (user.getCreditModifier()!= 0 && user.getCreditModifier() >=300) {
            user.setCreditModifier(300);
        } else{
            user.setCreditModifier(0);
        }

        userRepository.save(user);
        return user;
    }
    public User findUserById(Long id) {

        Optional<User> employeeOptional = userRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            System.out.println("no user found by id " + id);
        }

        return employeeOptional.get();

    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public User updateUser(User user){
        if(findUserById(user.getId()) != null) {
            userRepository.saveAndFlush(user);
        }

        return user;
    }

}
