package lk.ijse.hopeheavenbackend.service;


import lk.ijse.hopeheavenbackend.dto.UserDTO;

import java.util.List;

public interface UserService {
    int addUser(UserDTO userDTO);
    String generateNextUserId();
    List<UserDTO> getAllUsers();
    int updateUsers(UserDTO userDTO);
    int deleteUsers(String id);

    int login(UserDTO userDTO);
}
