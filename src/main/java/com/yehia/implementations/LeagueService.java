package com.yehia.implementations;

import com.yehia.entities.League;
import com.yehia.entities.User;
import com.yehia.repositories.LeagueRepository;
import com.yehia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;


    public List<League> getResult(){
        return leagueRepository.findAll();
    }

    public String theChampion(){
        League league = leagueRepository.findTheChampion();

        return league.getPlayer_id().getName();
    }


    public void sendMail(){
        League league = leagueRepository.findTheChampion();

        User player = league.getPlayer_id();

        String recipientAddress = player.getEmail();
        String subject = "Congratulation" ;

        String message = "Congratulation " + player.getName() + "for winning the League ! Great work and keep coming ";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }


}
