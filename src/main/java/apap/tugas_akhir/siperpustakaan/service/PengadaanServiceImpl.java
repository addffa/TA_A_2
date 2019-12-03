package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.repository.PengadaanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PengadaanServiceImpl implements PengadaanService{

    @Autowired
    PengadaanDb pengadaanDb;

    @Override
    public void tambahPengadaan(PengadaanBukuModel pengadaanModel){
        pengadaanDb.save(pengadaanModel);
    }
}
