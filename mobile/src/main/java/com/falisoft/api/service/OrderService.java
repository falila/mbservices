package com.falisoft.api.service;

import com.falisoft.common.dto.OrderRequest;
import com.falisoft.entity.Ordered;
import com.falisoft.exception.OrderCreationException;
import java.util.List;
import java.util.Map;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (09 2018)
 * @author Raphael KEITA
 * 
 */

public interface OrderService {

    Ordered findById(Long id);
    Ordered createOrder(OrderRequest orderRequest) throws OrderCreationException ;
    Ordered update(Ordered order);
    List<Ordered> findByCriteria(Map<String,String> criteria);
    
}
