package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        user.setPassword(encrypt(user.getPassword()));
        return userDb.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userDb.existsUserModelsByUsername(username);
    }

    private String encrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
