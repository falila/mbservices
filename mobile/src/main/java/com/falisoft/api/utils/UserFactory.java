package com.falisoft.api.utils;

import com.falisoft.common.dto.AccountDataDTO;
import com.falisoft.common.dto.AccountRequest;
import com.falisoft.common.dto.DeliveryboyCreateRequest;
import com.falisoft.common.dto.UserTypeEnum;
import com.falisoft.entity.Client;
import com.falisoft.entity.Deliveryboy;
import com.falisoft.entity.User;
import io.jsonwebtoken.lang.Assert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 *
 */
@Component
public class UserFactory {

    public static User createUser(AccountRequest user) {

        User myuser = new User(
                user.getUsername(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                true,
                UUID.randomUUID().toString().replaceAll("-", ""),
                user.getPhone());
        myuser.setLastPasswordResetDate(new Date());
        return myuser;
    }

    public static Deliveryboy createBoy(DeliveryboyCreateRequest user, User secUser) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Deliveryboy u = new Deliveryboy(
                user.getDriverLicenceNumber(),
                user.getEnginePlateNumber(),
                user.getEnginModel(),
                user.getEngineType(),
                format.parse(user.getLicenceIssueDate()),
                format.parse(user.getLicenceExpDate()),
                secUser.getReference(),
                user.getAddress()
        );
        return u;
    }

    public static Client createClient(AccountRequest account, User user) throws ParseException {

        Client client = new Client(
                user.getReference(),
                "C",
                account.getAddress(),
                "xxx");
        return client;
    }

    public static AccountDataDTO accountDataFromUser(User user) {

        AccountDataDTO adto = new AccountDataDTO(user);
        return adto;
    }

    public static AccountDataDTO accountDataFromClient(User user, Client client) {

        Assert.notNull(user, "user cannot to be null");
        Assert.notNull(client, "client cannot to be null");

        AccountDataDTO adto = accountDataFromUser(user);
        adto.setAddress(client.getAddress());
        adto.setType(client.getType());
        adto.setLocationcode(client.getLocationcode());
        adto.setId(client.getClientid());

        return adto;
    }

    public static AccountDataDTO accountDataFromBoy(User user, Deliveryboy boy) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Assert.notNull(user, "user cannot to be null");
        Assert.notNull(boy, "client cannot to be null");

        AccountDataDTO adto = accountDataFromUser(user);
        adto.setAddress(boy.getAddress());
        // for test purpose
        adto.setType(UserTypeEnum.B.name());
        adto.setDriverlicencenumber(boy.getDriverlicencenumber());
        adto.setEnginemodel(boy.getEnginemodel());
        adto.setEngineplatenumber(boy.getEngineplatenumber());
        adto.setEnginetype(boy.getEnginetype());
        adto.setLevel(boy.getLevel());
        adto.setLicenceexpdate(format.format(boy.getLicenceexpdate()));
        adto.setRating(boy.getRating());
        adto.setStatus(boy.getStatus());
        adto.setAddress(boy.getAddress());
        adto.setId(boy.getId());

        return adto;
    }
}
