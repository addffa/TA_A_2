package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import apap.tugas_akhir.siperpustakaan.repository.PeminjamanBukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        target.setTanggalPengembalian(peminjamanBukuModel.getTanggalPengembalian());
        target.setTangganPeminjaman(peminjamanBukuModel.getTangganPeminjaman());
        target.setBuku(peminjamanBukuModel.getBuku());
        target.setUser(peminjamanBukuModel.getUser());
        target.setStatus(peminjamanBukuModel.getStatus());
        peminjamanBukuDb.save(target);
        return target;
    }
}

