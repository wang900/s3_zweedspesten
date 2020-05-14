package server;

import communication.rest.dto.BaseResultDto;
import dataacces.datacontexts.InMemoryContext;
import dataacces.datacontexts.MSSqlDataContext;
import dataacces.datacontexts.interfaces.IDataContext;
import dto.AuthenticationRequestDto;
import serialization.GsonSerializer;
import server.dal.datamappers.DataMapperFactory;
import server.dal.repositories.AccountRepository;
import server.dal.repositories.IAccountRepository;
import server.dal.repositories.ITokenRepository;
import server.dal.repositories.TokenRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
public class AuthenticationRestService {

    private IAccountRepository accountRepository;
    private ITokenRepository tokenRepository;
    private GsonSerializer serializer;

    public AuthenticationRestService() {

        DataMapperFactory factory = new DataMapperFactory();
        IDataContext dataContext = new MSSqlDataContext(factory);
 //       IDataContext dataContext = new InMemoryContext();
        accountRepository = new AccountRepository(dataContext);
        tokenRepository = new TokenRepository(dataContext);
        serializer = new GsonSerializer();
    }

    //FOR TESTING PURPOSES
    public AuthenticationRestService(IAccountRepository playerRepos, ITokenRepository tokenRepos) {
        this.accountRepository = playerRepos;
        this.tokenRepository = tokenRepos;
    }

    @GET
    @Path("/user/get")
    public Response get() {
        return Response.status(200).entity(new BaseResultDto()).build();
    }

    @POST
    @Path("/user/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(String message) {
        AuthenticationRequestDto loginRequest = serializer.deserialize(message, AuthenticationRequestDto.class);
        if (loginRequest == null) {
            return Response.status(400).entity(ResponseHelper.getErrorResponseString()).build();
        }

        String token = accountRepository.login(tokenRepository, loginRequest.getUserName(), loginRequest.getHashedPassword());
        if (token.equals("")) {
            //TODO: log failed login requests
            return Response.status(401).entity(ResponseHelper.getErrorResponseString()).build();
        }

        return Response.status(200).entity(ResponseHelper.getLoginResultDtoResponseString(token)).build();

    }

    @POST
    @Path("/user/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(String message) {
        AuthenticationRequestDto loginRequest = serializer.deserialize(message, AuthenticationRequestDto.class);
        if (loginRequest == null) {
            return Response.status(400).entity(ResponseHelper.getErrorResponseString()).build();
        }

        boolean success = accountRepository.register(loginRequest.getUserName(), loginRequest.getHashedPassword());

        return Response.status(200).entity(ResponseHelper.getRegistrationResultDtoResponseString(success)).build();
    }

}
