package dto;

import communication.rest.dto.BaseResultDto;

public class LoginResultDto extends BaseResultDto {

        private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
