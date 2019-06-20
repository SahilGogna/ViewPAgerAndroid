package com.clas.lasalle.viewpager.database;

import com.clas.lasalle.viewpager.model.User;

import java.util.Collection;
import java.util.LinkedList;

public class UserDao {

    private LinkedList<User> list = new LinkedList<>();

    public UserDao(){
    }

    public UserDao(LinkedList<User> list) {
        this.list = list;
    }

    public boolean addUser(User user){
        if (list.contains(user)){
            return false;
        }else{
            list.add(user);
            return true;
        }
    }

    public boolean removeUser(User user){
        if (list.contains(user)){
            list.remove(user);
            return true;
        }else {
            return false;
        }
    }

    public boolean updateUser(User user, User newUser){
        if (list.contains(user)){
            list.remove(user);
            list.add(newUser);
            return true;
        }else {
            return false;
        }
    }

    public boolean loginUser(String email, String password){
        for (User user: list) {
            if ((user.getUserName().equals(email)) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public User getUserByEmail(String email){
        for (User user: list) {
            if (user.getUserName().equals(email)){
                return user;
            }

        }
        return null;
    }

    public User getUserByPassword(String password){
        for (User user: list) {
            if (user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public User getUser(User user){
        if (list.contains(user)){
            return list.get(list.indexOf(user));
        }else{
            return null;
        }
    }

    public User getUserByPosition(int position){
        if (list.size() -1 >= position){
            return list.get(position);
        }else{
            return null;
        }
    }

    public boolean contains(User user){
        return list.contains(user);
    }

    public boolean contains(String email) {
        for (User user : list) {
            if (user.getUserName().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void clear(){
        list.clear();
    }

    public Collection<User> getAll(){
        return  list;
    }
}
