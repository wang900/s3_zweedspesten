package server.dal.datamappers;

import dataacces.BaseDataMapper;
import dataacces.DataMapperFactoryBase;

public class DataMapperFactory extends DataMapperFactoryBase {


    @Override
    public BaseDataMapper getMapper(Class entityType) {
        switch (entityType.getCanonicalName())
        {
            case "dto.model.Account":
                return new AccountDataMapper();
            case "dto.model.Token":
                return new TokenDataMapper();
            default:
                return null;
        }
    }

    @Override
    public BaseDataMapper getMapper(String simpleType) {
        switch(simpleType)
        {
            case "Account":
                return new AccountDataMapper();
            case "Token":
                return new TokenDataMapper();
            default:
                return null;
        }
    }
}
