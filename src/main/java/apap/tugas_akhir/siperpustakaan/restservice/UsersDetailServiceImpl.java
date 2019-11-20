package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.UserDetailModel;
import apap.tugas_akhir.siperpustakaan.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersDetailServiceImpl implements UsersDetailService {

    @Autowired
    private UserDb userDb;

    @Override
    public List<UserDetailModel> getAllUserUsernameAndRoles() {
        return userDb.findAllBy();
    }
}
