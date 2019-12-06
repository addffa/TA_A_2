package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.AnggotaKoperasiDetail;

public interface KoperasiRestService {
    AnggotaKoperasiDetail getAnggotaKoperasi(String uuid, String role);
}
