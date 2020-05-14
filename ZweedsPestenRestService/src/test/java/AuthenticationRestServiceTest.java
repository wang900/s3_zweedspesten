import dataacces.DataMapperFactoryBase;
import dataacces.datacontexts.MSSqlDataContext;
import dataacces.datacontexts.interfaces.IDataContext;
import dto.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import serialization.GsonSerializer;
import serialization.interfaces.ISerializer;
import server.AuthenticationRestService;
import server.dal.datamappers.DataMapperFactory;
import server.dal.repositories.AccountRepository;
import server.dal.repositories.IAccountRepository;
import server.dal.repositories.ITokenRepository;
import server.dal.repositories.TokenRepository;

import javax.ws.rs.core.Response;

public class AuthenticationRestServiceTest {


    private AuthenticationRestService restService;

    @Before
    public void init()
    {
        DataMapperFactoryBase factory = new DataMapperFactory();
        IDataContext context = new MSSqlDataContext(factory);
        IAccountRepository playerRepos = new AccountRepository(context);
        ITokenRepository tokenRepos = new TokenRepository(context);
        restService = new AuthenticationRestService(playerRepos, tokenRepos);
    }

    @Test
    public void getAccount(){
        Response response = Response.ok().build();
        Assertions.assertEquals(response.getStatus() ,restService.get().getStatus());
    }

    @Test
    public void login(){
        ISerializer serializer = new GsonSerializer();
        Account account = new Account("test", "test");
        String message = String.valueOf(serializer.serialize(account));
        Response response = restService.login(message);
        Assertions.assertEquals(Response.ok().build().getStatus(), response.getStatus());
    }
}
