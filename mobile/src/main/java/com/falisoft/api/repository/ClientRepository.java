
package com.falisoft.api.repository;

import com.falisoft.entity.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByReference(String reference);
    List<Client> findByLocationcode(String code);

}
