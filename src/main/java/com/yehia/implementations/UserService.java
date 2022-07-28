package com.yehia.implementations;

import com.yehia.dto.UserDTO;
import com.yehia.entities.League;
import com.yehia.entities.User;
import com.yehia.repositories.LeagueRepository;
import com.yehia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String saveUser(UserDTO userDTO){

        User user = new User(userDTO.getName(),userDTO.getEmail());
        userRepository.save(user);
        League league = new League(0,0,0,0,0,user);
        leagueRepository.save(league);

        user.setLeague_id(league);
        userRepository.save(user);

        return "welcome" + user.getName();

    }

    public User findByName(String name){
        return userRepository.findUserByName(name);

    }
}
