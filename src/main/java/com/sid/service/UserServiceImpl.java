package com.sid.service;

import com.sid.dao.AppRoleRepository;
import com.sid.dao.AppUserRepository;
import com.sid.entities.AppRole;
import com.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public AppUser saveUser(String userName, String passWord, String confirmedPasseWord) {

        AppUser user = appUserRepository.findByUserName(userName);

        if (user != null) throw new RuntimeException("User Exciste");
        if (!passWord.equals(confirmedPasseWord)) throw new RuntimeException("passWord nN Confirmed");
        AppUser appUser = new AppUser();
        appUser.setPassWord(bCryptPasswordEncoder.encode(passWord));
        appUser.setUserName(userName);
        appUser.setActived(true);
        appUserRepository.save(appUser);
        addRoleToUser(userName, "USER");
        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser findUserByName(String userName) {
        return appUserRepository.findByUserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser = appUserRepository.findByUserName(userName);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getRoles().add(appRole);
    }
}
