package com.example.demo_2305.services;

import com.example.demo_2305.domain.Role;
import com.example.demo_2305.domain.UserDTO;
import com.example.demo_2305.domain.dao.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IUserRepository _userRepo;
    private final BCryptPasswordEncoder _passEncoder;

    public UserService() {
        _passEncoder = new BCryptPasswordEncoder();
    }
    public boolean registerUser(UserDTO user){
        try{
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setEmail(user.getEmail());
            user.setPassword(_passEncoder.encode(user.getPassword()));
            user.setConfirmPassword(user.getPassword());
            _userRepo.save(user);
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }


    private boolean emailExists(String email) {
        return _userRepo.findByEmail(email) != null;
    }

    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = _userRepo.findByName(username);
        if(user == null){
            System.out.printf("Not found:%s\n",username);
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(user.getUsername());
        return user;
    }
    public UserDTO findUserById(Long id){

        Optional<UserDTO> user = _userRepo.findById(id);
        return user.orElse(new UserDTO());
    }
    public Iterable<UserDTO> allUsers(){
        return _userRepo.findAll();
    }
    public boolean saveUser(UserDTO user){

        if(_userRepo.findByName(user.getUsername())!=null){
            return  false;
        }
        user.setRoles(Collections.singleton(new Role(1L,"User")));

        try{
            _userRepo.save(user);
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean deleteUser(Long id){
        boolean exists = _userRepo.existsById(id);
        if(exists)
            _userRepo.deleteById(id);

        return !exists;
    }
    public List<UserDTO> getUses_GT(Long minId){
        Query query = entityManager.createQuery("SELECT u FROM UserDTO u WHERE u.id > :minId");
        return query.getResultList();
    }

}
