package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.repository.BukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BukuServiceImpl implements BukuService {
    @Autowired
    BukuDb bukuDb;

    @Override
    public void tambahBuku(BukuModel bukuModel) {
        bukuDb.save(bukuModel);
    }
}
