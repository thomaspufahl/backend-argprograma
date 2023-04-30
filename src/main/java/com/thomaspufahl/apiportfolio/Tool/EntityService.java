package com.thomaspufahl.apiportfolio.Tool;

import com.thomaspufahl.apiportfolio.Portfolio.Person.PersonManager;
import com.thomaspufahl.apiportfolio.Security.model.User.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityService implements EntityManager {

    private final UserManager userManager;
    private final PersonManager personManager;

    @Override
    public UserManager getUserManager() {
        return userManager;
    }
    @Override
    public PersonManager getPersonManager() {
        return personManager;
    }
}
