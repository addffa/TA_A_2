package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.PegawaiDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;

public interface UserRestService {
    PegawaiDetail postUserPegawaiToSiSivitas(PegawaiDetail user);
    ResultDetail getUserProfile(String uuid);
}
