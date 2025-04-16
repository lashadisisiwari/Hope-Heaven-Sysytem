package lk.ijse.hopeheavenbackend.service.impl;
import lk.ijse.hopeheavenbackend.dto.UserDTO;
import lk.ijse.hopeheavenbackend.entity.User;
import lk.ijse.hopeheavenbackend.repository.UserRepo;
import lk.ijse.hopeheavenbackend.service.UserService;
import lk.ijse.hopeheavenbackend.utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String generateNextUserId(){
        long count = userRepo.count()+1;
        return String.format("UID-%04d", count);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        if(userRepo.findAll().isEmpty()){
            return null;
        }
        else{
           return modelMapper.map(userRepo.findAll(),new TypeToken<List<UserDTO>>(){}.getType());
        }
    }

    @Override
    public int updateUsers(UserDTO userDTO) {
        if(userRepo.existsById(userDTO.getId())){
            userRepo.save(modelMapper.map(userDTO,User.class));
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int deleteUsers(String id) {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int login(UserDTO userDTO) {
        // 1. Find user by username or email
        User user = userRepo.findByEmail( userDTO.getEmail());

        // 2. Check if user exists
        if (user == null) {
            return VarList.Not_Found; // User not found
        }

        // 3. Verify password with BCrypt
        if (BCrypt.checkpw(userDTO.getPassword(), user.getPassword())) {
            return VarList.OK; // Successful login
        } else {
            return VarList.Not_Acceptable; // Wrong password
        }
    }


    @Override
    public int addUser(UserDTO userDTO) {
        if(userRepo.existsById(userDTO.getId())){
            return VarList.Not_Acceptable;
        }
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }
}
