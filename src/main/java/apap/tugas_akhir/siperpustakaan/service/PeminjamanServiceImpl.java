package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.repository.PeminjamanBukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PeminjamanServiceImpl implements PeminjamanService {
    @Autowired
    PeminjamanBukuDb peminjamanBukuDb;

    @Override

    public PeminjamanBukuModel getPinjamanbyId(Integer idPinjaman){
        return peminjamanBukuDb.findById(idPinjaman).get();
    }

    @Override
    public PeminjamanBukuModel changeStatusPeminjaman(PeminjamanBukuModel peminjamanBukuModel){
        PeminjamanBukuModel target = peminjamanBukuDb.findById(peminjamanBukuModel.getId()).get();
        target.setStatus(peminjamanBukuModel.getStatus());
        peminjamanBukuDb.save(target);
        return target;
    }

    @Override
    public List<PeminjamanBukuModel> getListPeminjamanBuku() {
        return peminjamanBukuDb.findAll();
    }

    @Override
    public List<PeminjamanBukuModel> getListPeminjamanBukuByUser(UserModel user) {
        return peminjamanBukuDb.findByUser(user);
    }
}
