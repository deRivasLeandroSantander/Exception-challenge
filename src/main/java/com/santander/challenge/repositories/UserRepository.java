package com.santander.challenge.repositories;

import com.santander.challenge.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
    public Boolean save(User user) {
        //This method should save the user in the DB and then return true, false otherwise
        return true;
    }
}
