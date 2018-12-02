package factory;

import java.util.ArrayList;
import domain.User;

public class FakeUserFactory implements  IUserFactory {
    private final ArrayList<User> Users;

    public FakeUserFactory() {
        this.Users = new ArrayList<User>(){{
            add(new User("sune@student.sdu.dk"));
            add(new User("anna@student.sdu.dk"));
            add(new User("veena@student.sdu.dk"));
            add(new User("admira@student.sdu.dk"));
       }};
    }

    public ArrayList<User> getUsers() {
        return Users;
    }
}
