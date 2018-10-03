package com.falisoft.api.service;

import com.falisoft.common.dto.OrderRequest;
import com.falisoft.entity.Ordered;
import com.falisoft.security.repository.OrderRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (09 2018)
 * @author Raphael KEITA
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repos;

    @Override
    public Ordered findById(Long id) {
        Ordered ord  = null ;
        try {
            return repos.findById(id).get();
        } catch (Exception ex) {
            
        }
        return ord;
    }

    /**
     *
     * @param orderRequest
     * @return
     * @throws Exception
     */
    @Override
    public Ordered createOrder(OrderRequest orderRequest) {
        //TODO  bing orderRequest with new one which is going to be persist
        Ordered ord = new Ordered();
        repos.save(ord);
        return ord;
    }

    @Override
    public Ordered update(Ordered order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordered> findByCriteria(Map<String, String> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
