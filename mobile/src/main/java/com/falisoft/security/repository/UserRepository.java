package com.falisoft.security.repository;

import com.falisoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by raphael on 20.03.17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    @Query(value = "SELECT user_seq.nextval FROM dual", nativeQuery = true)
    Long getNextSeriesId();

    public User findByReference(String reference);
}
