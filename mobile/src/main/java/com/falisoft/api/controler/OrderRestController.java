package com.falisoft.api.controler;

import com.falisoft.api.service.OrderService;
import com.falisoft.common.dto.OrderRequest;
import com.falisoft.entity.Ordered;
import com.falisoft.exception.OrderCreationException;
import java.util.Map;
import java.util.logging.Level;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (09 2018)
 * @author Raphael KEITA
 *
 */
@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "${api.route.order.id.path}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrderByID(@RequestParam(value = "id", required = true) String id) {
        logger.info(" getOrderByID  id: " + id);
        try {
            return ResponseEntity.ok(orderService.findById(Long.valueOf(id)));
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(OrderRestController.class.getName()).log(Level.SEVERE, null, e);
        }
        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "${api.route.order.all.path}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllOrders() {
        logger.info(" getAllOrders ");
        Map<String, String> criteria = null;
        return ResponseEntity.ok(orderService.findByCriteria(criteria));

    }

    @RequestMapping(value = "${api.route.order.all.path}", method = RequestMethod.PUT)
    public ResponseEntity<Ordered> createOrder(@Valid OrderRequest orderRequest) throws OrderCreationException {
        logger.info(" createOrder ");
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @RequestMapping(value = "${api.route.order.all.path}", method = RequestMethod.POST)
    public ResponseEntity<Ordered> updateOrder(@Valid Ordered order) {
        logger.info(" updateOrder ");
        return ResponseEntity.ok(orderService.update(order));

    }

}
