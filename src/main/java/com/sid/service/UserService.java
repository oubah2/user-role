package com.sid.service;

import com.sid.entities.AppRole;
import com.sid.entities.AppUser;

public interface UserService {

    public AppUser saveUser(String userName, String passWord, String confirmedPasseWord);
    public AppRole  saveRole(AppRole role);
    public AppUser findUserByName(String userName);
    public void addRoleToUser(String userName , String roleName);
}
