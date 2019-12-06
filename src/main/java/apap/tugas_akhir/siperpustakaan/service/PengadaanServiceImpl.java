package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.repository.PengadaanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PengadaanServiceImpl implements PengadaanService {
    @Autowired
    PengadaanDb pengadaanDb;


    @Override
    public PengadaanBukuModel getPengadaanById(int idPengadaan) {
        return pengadaanDb.findById(idPengadaan).get();
    }

    @Override
    public boolean hapusPengadaan(int idPengadaan, String role) {
        PengadaanBukuModel pengadaan = getPengadaanById(idPengadaan);
        if((role.equals("Pustakawan") && pengadaan.getStatus() < 2)
            || ((role.equals("Guru") || role.equals("Siswa")) && pengadaan.getStatus() < 1) ) {
            pengadaanDb.delete(getPengadaanById(idPengadaan));
            return true;
        } else {
            return false;
        }
    }
}
