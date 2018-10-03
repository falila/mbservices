package com.falisoft.rest;

import com.falisoft.api.service.IClient;
import com.falisoft.api.service.UserService;
import com.falisoft.api.utils.UserFactory;
import com.falisoft.common.dto.AccountDataDTO;
import com.falisoft.common.dto.AccountRequest;
import com.falisoft.common.dto.DeliveryboyCreateRequest;
import com.falisoft.entity.Client;
import com.falisoft.entity.User;
import com.falisoft.exception.AccountCreationValidationExcpetion;
import com.falisoft.security.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.jsonwebtoken.lang.Assert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.catalina.util.URLEncoder;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class AccountRestControllerTest {

    private MockMvc mvc;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IClient clientService;
    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext context;
    private AccountRequest clientDto;
    private DeliveryboyCreateRequest deliveryBoyDto;

    @Autowired
    private ObjectMapper objectMapper;
    private JacksonTester< AccountRequest> clientDTOJsonTester;
    private JacksonTester< AccountRequest> clientDTOJsonTesterForUpdate;
    private JacksonTester< AccountDataDTO> userDtoJsonTester;
    private JacksonTester< DeliveryboyCreateRequest> deliveryBoyDTOJsonTester;
    private JacksonTester< DeliveryboyCreateRequest> deliveryBoyDTOJsonTesterForUpdate;
    private static final SimpleDateFormat SIMPLEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * *client fields *
     */
    private static final String FIRSTNAME = "cliefirtname ";
    private static final String LASTNAME = "clielastName ";
    private static final String USERNAME = "clieusername ";
    private static final String PASSWORD = "cliepassword ";
    private static final String REFERENCE = "cliereference";
    private static final String EMAIL = "clientemail@email.com";
    private static final String PHONE = "012454783";
    private static final String ADDRESS = " 12454 address";
    private static final String TYPE = "C";

    /**
     * * delivryboy fields ***
     */
    private static final String BFIRSTNAME = "boyfirtname ";
    private static final String BLASTNAME = "boylastName ";
    private static final String BUSERNAME = "boyusername ";
    private static final String BPASSWORD = "boypassword ";
    private static final String BREFERENCE = "boyreference";
    private static final String BEMAIL = "boy@email.com";
    private static final String BPHONE = "3265987";
    private static final String BADDRESS = "001 address";
    private static final String BTYPE = "B";
    private static final String BDRIVLICNUMB = "UIE54781LL";
    private static final String BENGPLATENUMB = "547781L";
    private static final String BENGMOD = "XXX";
    private static final String BENGTYPE = "moto";
    private static final String BLICISSUEDATE = "2010-10-25";
    private static final String BLICEXPDATE = "2022-11-22";

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        JacksonTester.initFields(this, objectMapper);
        clientDto
                = new AccountRequest(
                        USERNAME,
                        FIRSTNAME,
                        LASTNAME,
                        PASSWORD,
                        EMAIL,
                        REFERENCE,
                        PHONE,
                        TYPE,
                        ADDRESS);

        deliveryBoyDto
                = new DeliveryboyCreateRequest(
                        0,
                        0,
                        BDRIVLICNUMB,
                        BENGPLATENUMB,
                        BENGMOD,
                        BENGTYPE,
                        BLICISSUEDATE,
                        BLICEXPDATE,
                        BUSERNAME,
                        BFIRSTNAME,
                        BLASTNAME,
                        BPASSWORD,
                        BEMAIL,
                        BREFERENCE,
                        BPHONE,
                        BTYPE,
                        BADDRESS
                );
    }

    @Test
    public void shouldGetUnauthorizedWithoutRole() throws Exception {

        this.mvc.perform(get("/api/v1/clients"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getClientSuccessfullyByReference() throws Exception {

        this.mvc.perform(get("/api/v1/clients").param("ref", "ref01")).andExpect(status().isOk())
                .andExpect(content().string(containsString("clientid")));
    }

    @Test
    @WithMockUser(roles = "DRIVER")
    public void getBoySuccessfullyByReference() throws Exception {

        this.mvc.perform(get("/api/v1/boys").param("ref", "boy01")).andExpect(status().isOk())
                .andExpect(content().string(containsString("LIC7854")));
    }

    //*********** tested creation account ******//
    private static Date parseDate(final String dateString) {
        try {
            return SIMPLEFORMAT.parse(dateString);
        } catch (final ParseException e) {
            return new Date();
        }
    }

    @Test
    @WithMockUser(roles = "USER")
    public void createClientAccout() throws Exception {
        final String clientDtoJson = clientDTOJsonTester.write(clientDto).getJson();
        final String clientRequestParameter = clientDtoJson;

        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.put("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientRequestParameter)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        AccountDataDTO parsedResponse = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), AccountDataDTO.class);
        Assert.notNull(parsedResponse);
        Assert.notNull(parsedResponse.getId());
        Assert.notNull(parsedResponse.getReference());
        Assert.isTrue(parsedResponse.getUserName().equals(USERNAME), "same username");
        Assert.isTrue(parsedResponse.getEmail().equals(EMAIL), "the same email");

    }

    @Test
    @WithMockUser(roles = "USER")
    public void updateClientAccout() throws Exception {
        AccountRequest clientDtoJsonUpdate = new AccountRequest("USERNAME1", "FIRSTNAME1", "LASTNAME1", "PASSWORD1", "EMAIL1", "REFERENCE1", "PHONE1", "TYPE1", "ADDRESS1");

        final String clientDtoJson = clientDTOJsonTesterForUpdate.write(clientDtoJsonUpdate).getJson();
        final String clientRequestParameter = clientDtoJson;

        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.put("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientRequestParameter)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        AccountDataDTO parsedResponse = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), AccountDataDTO.class);

        Assert.notNull(parsedResponse);

        Assert.notNull(parsedResponse.getId());
        Assert.notNull(parsedResponse.getReference());

        // check if the associate client is created
        Client findByReference = clientService.findByReference(parsedResponse.getReference());
        Assert.notNull(findByReference.getClientid());

        // preparing to update client account 
        User user = userService.findByReference(parsedResponse.getReference());
        Assert.notNull(user);
        // set phone 
        user.setPhone("123456788");
        user.setFirstname("ll1l1");
        
        AccountDataDTO adto = UserFactory.accountDataFromClient(user, findByReference);
        
        final String userDtoJson = userDtoJsonTester.write(adto).getJson();
        final String useRequestParameter = userDtoJson;

        MvcResult mvcResult1 = mvc
                .perform(MockMvcRequestBuilders.post("/api/v1/clients/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(useRequestParameter)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // read objet form stream 
        AccountDataDTO returnUserValue = mapper.readValue(mvcResult1.getResponse().getContentAsByteArray(), AccountDataDTO.class);
        logger.info(userDtoJsonTester.write(returnUserValue).getJson());
        Assert.notNull(returnUserValue);
        Assert.isTrue(returnUserValue.getPhone().equals("123456788"));
        Assert.isTrue(returnUserValue.getFirstname().equals("ll1l1"));

    }

    @Test
    @WithMockUser(roles = "DRIVER")
    public void createBoyAccout() throws Exception {
        final String deliveryBoyDtoJson = deliveryBoyDTOJsonTester.write(deliveryBoyDto).getJson();
        final String boyRequestParamater = deliveryBoyDtoJson;

        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.put("/api/v1/boys/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boyRequestParamater)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        AccountDataDTO parsedResponse = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), AccountDataDTO.class
        );
        Assert.notNull(parsedResponse);
        Assert.notNull(parsedResponse.getId());
        Assert.notNull(parsedResponse.getReference());
        Assert.isTrue(parsedResponse.getUserName().equals(BUSERNAME), "same busername");
        Assert.isTrue(parsedResponse.getEmail().equals(BEMAIL), "the same bemail");
    }

    @Test(expected = MismatchedInputException.class)
    @WithMockUser(roles = "DRIVER")
    public void createBoyAccoutFailed() throws Exception {

        DeliveryboyCreateRequest deliveryBoyDto1 = new DeliveryboyCreateRequest();
        final String deliveryBoyDtoJson = deliveryBoyDTOJsonTester.write(deliveryBoyDto1).getJson();
        final String boyRequestParamater = deliveryBoyDtoJson;

        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.put("/api/v1/boys/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boyRequestParamater)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        AccountDataDTO parsedResponse = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), AccountDataDTO.class
        );
        Assert.notNull(parsedResponse);
        Assert.notNull(parsedResponse.getId());
        Assert.notNull(parsedResponse.getReference());
        Assert.isTrue(parsedResponse.getUserName().equals(BUSERNAME), "same busername");
        Assert.isTrue(parsedResponse.getEmail().equals(BEMAIL), "the same bemail");
    }
   
}
