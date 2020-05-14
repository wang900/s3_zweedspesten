package server.dal.repositories;

import dataacces.IRepository;
import dto.model.Account;

public interface IAccountRepository extends IRepository<Account> {
    String login(ITokenRepository tokenRepos, String userName, String password);
    boolean register(String username, String password);
}
