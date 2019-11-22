package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.UserDetail;

public interface UserRestService {
    String postUserToSiSivitas(UserDetail user, String role);
    UserDetail getUserProfile(String uuid, String role);
}
