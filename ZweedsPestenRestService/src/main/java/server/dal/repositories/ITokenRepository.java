package server.dal.repositories;

import dataacces.IRepository;
import dto.model.Token;

public interface ITokenRepository extends IRepository<Token> {
    Token getTokenForAccount(long userId);
    String generateToken(long userId);
}
