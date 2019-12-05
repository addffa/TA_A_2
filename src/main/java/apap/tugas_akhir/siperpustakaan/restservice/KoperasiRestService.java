package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;

public interface KoperasiRestService {
    PengadaanDetail getAnggotaKoperasi(String uuid, String role);
}
