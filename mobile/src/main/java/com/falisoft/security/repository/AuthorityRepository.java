package com.falisoft.security.repository;

import com.falisoft.entity.Authority;
import com.falisoft.entity.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(AuthorityName name);
    @Query(value = "SELECT authority_seq.nextval FROM dual", nativeQuery = true)
    Long getNextSeriesId();
}
