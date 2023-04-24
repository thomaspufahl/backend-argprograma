package com.thomaspufahl.apiportfolio.Tool;

import com.thomaspufahl.apiportfolio.Portfolio.Employment.EmploymentManager;
import com.thomaspufahl.apiportfolio.Portfolio.Person.PersonManager;
import com.thomaspufahl.apiportfolio.Security.model.User.UserManager;

public interface EntityManager {
    UserManager getUserManager();
    PersonManager getPersonManager();
    EmploymentManager getEmploymentManager();
}
