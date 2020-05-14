package server.dal.repositories;

import dataacces.RepositoryBase;
import dataacces.datacontexts.interfaces.IDataContext;
import dto.model.Account;
import dto.model.Token;

import java.util.List;

public class AccountRepository extends RepositoryBase<Account> implements IAccountRepository {

    public AccountRepository(IDataContext context)
    {
        super(context);
    }

    public String login(ITokenRepository tokenRepos, String userName, String password)
    {
        List<Account> players = getAll();
        for(Account p : players)
        {
            if(p.getUsername().equals(userName)
                    && p.getPassword().equals(password))
            {
                //Check existing token
                Token existingToken = tokenRepos.getTokenForAccount(p.getEntityId());
                if(existingToken != null)
                    return existingToken.getTokenText();

                //OLD TOKEN NOT FOUND SO GENERATE A NEW ONE
                return tokenRepos.generateToken(p.getEntityId());
            }
        }
        return "";
    }

    public boolean register(String username, String password) {
        List<Account> players = getAll();
        for (Account p : players) {
            if (p.getUsername().equals(username)
                    && p.getPassword().equals(password)) {
                return false;
                //USER ALREADY EXISTS
            }
        }

        Account player = new Account(username, password);
        add(player);
        return true;

    }
}
