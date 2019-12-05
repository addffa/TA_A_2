package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.repository.PengadaanBukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PengadaanServiceImpl implements PengadaanService {
    @Autowired
    PengadaanBukuDb pengadaanBukuDb;

    @Override
    public List<PengadaanBukuModel> getListPengadaanBuku() {
        return pengadaanBukuDb.findAll();
    }

    @Override
    public List<PengadaanBukuModel> getListPengadaanBukuByUser(UserModel user) {
        return pengadaanBukuDb.findByUser(user);
    }
}
