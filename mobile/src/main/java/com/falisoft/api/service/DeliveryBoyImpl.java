/*
 * __NAME__.java
 *
 * Created on __DATE__, __TIME__
 *
 * Copyright(c) {2017} Falisoft, Inc.  All Rights Reserved.
 * This software is the proprietary information of Falisoft.
 *
 */

package com.falisoft.api.service;

import com.falisoft.api.repository.DeliveryBoyRepository;
import com.falisoft.entity.Deliveryboy;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
@Service
public class DeliveryBoyImpl implements IDeliveryBoy{
    
    @Autowired
    private DeliveryBoyRepository repo;

    @Override
    @Transactional
    public Deliveryboy findByReference(String reference) {
        Deliveryboy boy = null;
         try{
            boy =repo.findByReference(reference);
        }catch(Exception e){
            
        }finally{
            return boy;
        }     
    }

    @Override
    @Transactional
    public Deliveryboy savDeliveryboy(Deliveryboy b) {
       return  repo.saveAndFlush(b);
    }

}
