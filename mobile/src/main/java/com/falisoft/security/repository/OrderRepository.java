
package com.falisoft.security.repository;

import com.falisoft.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
public interface OrderRepository extends JpaRepository<Ordered, Long>{
    

}
