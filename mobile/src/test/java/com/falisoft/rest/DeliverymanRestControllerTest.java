/*
 * __NAME__.java
 *
 * Created on __DATE__, __TIME__
 *
 * Copyright(c) {2017} Falisoft, Inc.  All Rights Reserved.
 * This software is the proprietary information of Falisoft.
 *
 */
package com.falisoft.rest;

import com.falisoft.security.JwtTokenUtil;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliverymanRestControllerTest {

    private MockMvc mvc;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    // Test 1 A delivery man can see all his tasks
    @Test
    @WithMockUser(roles = "DRIVER")
    public void getDeliveriesByUserRef() throws Exception {

        this.mvc.perform(get("/api/v1/boys").param("ref", "boy01")).andExpect(status().isOk())
                .andExpect(content().string(containsString("LIC7854")));
    }

    // test he can change order status 
    //  test 3  he can close one of his tasks.
}
