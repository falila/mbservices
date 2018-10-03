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

import com.falisoft.entity.Deliveryboy;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
public interface IDeliveryBoy {

    public Deliveryboy findByReference(String reference);
    Deliveryboy savDeliveryboy(Deliveryboy b);

}
