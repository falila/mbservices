package com.falisoft.api.service;

import com.falisoft.entity.User;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
interface IUsers {

    User createUser(User user);
    User updateUser(User user);    
    Long getNextSeqId();
    User findByReference(String reference);

}
