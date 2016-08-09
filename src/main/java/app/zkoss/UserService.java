package app.zkoss;

import app.hibernate.User;
import app.hibernate.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by saifularifin on 6/22/2016.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAll(){
        return userDao.getAll();
    }

    public void insert(User user){
         userDao.create(user);
    }
}
