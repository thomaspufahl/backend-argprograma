package com.thomaspufahl.apiportfolio.Tool.Entity;

import com.thomaspufahl.apiportfolio.Portfolio.Person.PersonManager;
import com.thomaspufahl.apiportfolio.Security.model.User.UserManager;

public interface EntityManager {
    UserManager getUserManager();
    PersonManager getPersonManager();
}
