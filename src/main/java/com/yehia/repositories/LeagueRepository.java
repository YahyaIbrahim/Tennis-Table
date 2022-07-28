package com.yehia.repositories;

import com.yehia.entities.League;
import com.yehia.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends CrudRepository<League,Long> {

    List<League> findAll();

    @Query(value = "select * from tennis.league order by pts desc limit 1", nativeQuery = true)
    League findTheChampion();


    @Query(value = "select * from tennis.league where player_id = :player", nativeQuery = true)
    League findFirstByPlayer_id(@Param("player") int id);
}
