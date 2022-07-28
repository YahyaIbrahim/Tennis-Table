package com.yehia.repositories;

import com.yehia.entities.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Long> {

    List<Schedule> findAll();

    @Query(value = "select * from schedule where home_player_id = :home and away_player_id = :away",nativeQuery = true)
    Schedule findAllByHomePlayerIdAndAwayPlayerId(@Param("home") int home,@Param("away") int away);

}
