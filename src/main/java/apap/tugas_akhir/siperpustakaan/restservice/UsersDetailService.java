package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.UserDetailModel;

import java.util.List;

public interface UsersDetailService {
    List<UserDetailModel> getAllUserUsernameAndRoles();
}
