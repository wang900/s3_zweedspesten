package client;

import communication.rest.BaseRestClient;
import dto.AuthenticationRequestDto;
import dto.LoginResultDto;
import dto.RegistrationResultDto;

public class AuthenticationRestClient extends BaseRestClient implements IAuthRestClient {


    private String url;

    public AuthenticationRestClient(){}

    public AuthenticationRestClient(String url) {
        this.url = url;
    }

    public String getBaseUr() {
        return url;
    }

    public boolean register(String username, String password) {
        AuthenticationRequestDto dto = new AuthenticationRequestDto(username, password);
        String query = "/user/register";
        RegistrationResultDto result = executeQueryPost(dto, query, RegistrationResultDto.class);
        return result.isSuccess();
    }

    public String login(String username, String password) {
        AuthenticationRequestDto dto = new AuthenticationRequestDto(username, password);
        String query = "/user/login";
        LoginResultDto result =  executeQueryPost(dto, query, LoginResultDto.class);
        return result.getToken();
    }
}
