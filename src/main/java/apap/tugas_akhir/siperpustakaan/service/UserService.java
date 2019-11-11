package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);

    boolean isUsernameExists(String username);
}
