package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;

public interface PengadaanRestService {
    PengadaanDetail getAnggotaKoperasi(String uuid, String role);
}
