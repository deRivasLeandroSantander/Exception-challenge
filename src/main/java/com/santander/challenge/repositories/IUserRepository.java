package com.santander.challenge.repositories;

import com.santander.challenge.models.User;

public interface IUserRepository {
    public Boolean save(User user);
}
