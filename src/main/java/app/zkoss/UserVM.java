package app.zkoss;

import app.hibernate.User;
import app.hibernate.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifularifin on 6/22/2016.
 */
@Component
@VariableResolver(DelegatingVariableResolver.class)
public class UserVM {

    private User user = new User();
    private List<User> userList;
    @WireVariable
    private UserService userService;

    @Command
    @NotifyChange("userList")
    public void retrieveUser(){
        try {
            userList = new ArrayList<>();
            userList = userService.getAll();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userService.getAll();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Command
    @NotifyChange({ "userList","user" })
    public void submit(){
        userService.insert(user);
        retrieveUser();
    }
}
