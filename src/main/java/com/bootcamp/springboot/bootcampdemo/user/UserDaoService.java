package com.bootcamp.springboot.bootcampdemo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.Object;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8080", "http://localhost:8081" },  allowedHeaders = "*")
@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static List<Address> addresses = new ArrayList<>();
    private static List<Company> companies = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Diana", "diana22@yahoo.com", "092929211", "diana", "www.google.com",
                new Address( "steert", "apt 55", "Amsterdam", "2998-3874"), new Company("Everis", "Everis", "about it" )));
        users.add(new User(2, "Rose", "roses2@yahoo.com", "092929211", "rose","www.google.com",
                new Address( "steert", "apt 55", "Amsterdam", "2998-3874"), new Company("Everis", "Everis", "about it" )));
        users.add(new User(3, "Rachimo", "rachimo@yahoo.com", "092929211", "rachimo","www.google.com",
                new Address( "steert", "apt 55", "Amsterdam", "2998-3874"), new Company("Everis", "Everis", "about it" )));
//        addresses.add(new Address( "steert", "apt 55", "Amsterdam", "2998-3874"));
//        companies.add(new Company("Everis", "Everis", "about it" ));
    }


public List<User> findAll() {
    return users;
}

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        UserDaoService.users = users;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public static void setAddresses(List<Address> addresses) {
        UserDaoService.addresses = addresses;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public static void setCompanies(List<Company> companies) {
        UserDaoService.companies = companies;
    }

    public static int getUsersCount() {
        return usersCount;
    }

    public static void setUsersCount(int usersCount) {
        UserDaoService.usersCount = usersCount;
    }

    public User save(User user) {
    if (user.getId()==null) {
        user.setId(++usersCount);
    }
    users.add(user);
    return user;
}

public User findOne(int id) {
    for (User user:users) {
        if (user.getId()==id) {
            return user;
        }
    }
    return null;
}

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
