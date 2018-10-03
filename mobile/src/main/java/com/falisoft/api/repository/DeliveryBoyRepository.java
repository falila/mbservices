package com.falisoft.api.repository;

import com.falisoft.entity.Deliveryboy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
public interface DeliveryBoyRepository extends JpaRepository<Deliveryboy, Long>{

    Deliveryboy findByReference(String reference);
}
