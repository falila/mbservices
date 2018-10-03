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

import com.falisoft.entity.User;
import com.falisoft.security.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2018)
 * @author Raphael KEITA
 *
 */
@Service
public class UserService implements IUsers {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Long getNextSeqId() {
        return userRepository.getNextSeriesId();
    }

    @Override
    public User findByReference(String reference) {
        return userRepository.findByReference(reference);
    }

}
