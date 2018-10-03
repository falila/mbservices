package com.falisoft.api.controler;

import com.falisoft.exception.AccountUpdateExcpetion;
import com.falisoft.api.service.IClient;
import com.falisoft.api.service.IDeliveryBoy;
import com.falisoft.common.dto.DeliveryboyCreateRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.falisoft.api.service.UserService;
import com.falisoft.api.utils.UserFactory;
import com.falisoft.common.dto.AccountDataDTO;
import com.falisoft.common.dto.AccountRequest;
import com.falisoft.common.dto.UserTypeEnum;
import com.falisoft.entity.Authority;
import com.falisoft.entity.AuthorityName;
import com.falisoft.entity.Client;
import com.falisoft.entity.Deliveryboy;
import com.falisoft.entity.User;
import com.falisoft.exception.AccountCreationValidationExcpetion;
import com.falisoft.security.repository.AuthorityRepository;
import io.jsonwebtoken.lang.Assert;
import java.text.ParseException;
import static javafx.scene.input.KeyCode.M;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (09 2018)
 * @author Raphael KEITA
 *
 */
@RestController
public class AccountRestController {

    @Autowired
    private IDeliveryBoy boyService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository authrepos;

    @Autowired
    private PasswordEncoder encryp;

    @Autowired
    private IClient ClientService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "${api.route.boy.reference.path}", method = RequestMethod.GET)
    public ResponseEntity<?> getBoyByReference(@RequestParam(value = "ref", required = true) String reference) {
        logger.info(" getBoyByReference  reference: " + reference);
        return ResponseEntity.ok(boyService.findByReference(reference));

    }

    @RequestMapping(value = "${api.route.client.reference.path}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> getClientByReference(@RequestParam(value = "ref", required = true) String reference) {
        logger.info(" getClientByReference  reference: " + reference);
        return ResponseEntity.ok(ClientService.findByReference(reference));
    }

    @RequestMapping(value = "${api.route.boy.create.path}", method = RequestMethod.PUT)
    public ResponseEntity<AccountDataDTO> createBoyAccount(@Valid @RequestBody DeliveryboyCreateRequest account) throws AccountCreationValidationExcpetion {
        logger.info(" createBoyAccount  : " + account);
        User user = UserFactory.createUser(account);
        //encryp password
        user.setPassword(encryp.encode(user.getPassword()));
        AccountDataDTO accountData = null;

        try {
            Authority auth = authrepos.findByName(AuthorityName.ROLE_DRIVER);
            user.getAuthorities().add(auth);
            auth.getUsers().add(user);
            Deliveryboy createBoy = UserFactory.createBoy(account, user);
            user.setId(userService.getNextSeqId());
            userService.createUser(user);
            boyService.savDeliveryboy(createBoy);
            Assert.notNull(user.getId(), " The user id has to be different form  null");
            accountData = UserFactory.accountDataFromBoy(user, createBoy);

        } catch (ParseException ex) {
            throw new AccountCreationValidationExcpetion(ex.getMessage(), ex);
        }
        return ResponseEntity.ok(accountData);
    }

    @RequestMapping(value = "${api.route.client.create.path}", method = RequestMethod.PUT)
    @Transactional
    public ResponseEntity<AccountDataDTO> createClientAccount(@Valid @RequestBody AccountRequest account) throws AccountCreationValidationExcpetion {
        logger.info(" createClientAccount  : " + account);
        User user = UserFactory.createUser(account);
        // password encryption
        user.setPassword(encryp.encode(user.getPassword()));
        // data to populate and retourn to a client
        AccountDataDTO accountData = null;
        try {
            Authority auth = authrepos.findByName(AuthorityName.ROLE_CLIENT);
            user.getAuthorities().add(auth);
            auth.getUsers().add(user);
            user.setId(userService.getNextSeqId());
            userService.createUser(user);
            //creating client data
            Client client = UserFactory.createClient(account, user);
            ClientService.save(client);
            Assert.notNull(user.getId(), " The user id has to be different form  null");
            accountData = UserFactory.accountDataFromClient(user, client);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            throw new AccountCreationValidationExcpetion(ex.getMessage(), ex);
        }
        return ResponseEntity.ok(accountData);
    }

    @RequestMapping(value = "${api.route.account.update.path}", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<AccountDataDTO> updateUserAccount(@RequestBody AccountDataDTO account) throws AccountUpdateExcpetion {
        logger.debug(" updateUserAccount  : " + account);
        User user = null;
        AccountDataDTO accountData = null;

        try {
            Assert.notNull(account, "The account you are trying to update cannot be null");
            user = userService.findByReference(account.getReference());
            Assert.notNull(user, "cannot find any user ");

            //** only few fields can be updated such  as lastname, phone, address
            // binding values
            user.setFirstname(account.getFirstname());
            user.setPhone(account.getPhone());
            user.setLastname(account.getLastname());
            userService.updateUser(user);
            accountData = UserFactory.accountDataFromUser(user);

        } catch (Exception ex) {
            logger.info(ex.getMessage());
            throw new AccountUpdateExcpetion(ex.getMessage(), ex);
        }
        return ResponseEntity.ok(accountData);
    }

    private boolean checkUserType(String type) {

        switch (UserTypeEnum.valueOf(type)) {
            case B: // boy

                break;
            case C: // client

                break;
            case D:  // dispatch

                break;
            case A: // admin

                break;
            case U:  // user

                break;
            case M: // managaer
                break;
        }
        return true;

    }
}
