package com.chichi.repository;

import com.chichi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author  lanhnguyen on 10/08/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where profileId=?1")
    User findByProfileId(String profileId);
}
