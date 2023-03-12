package md.fin.homefinance.services;

import md.fin.homefinance.dto.UserDTO;
import md.fin.homefinance.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    //Security
    boolean save(UserDTO userDTO);
    void save(User user);
    List<UserDTO> getAll();

    User findByName(String name);
    void updateProfile(UserDTO userDTO);
}

