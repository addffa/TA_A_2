package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.repository.BukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BukuServieImpl implements BukuService {

    @Autowired
    BukuDb bukuDb;

    @Override
    public BukuModel getBukuById (Integer idBuku){
        return bukuDb.findById(idBuku).get();
    }

    @Override
    public void hapusBuku(Integer idBuku){
        BukuModel buku = getBukuById(idBuku);
        bukuDb.delete(buku);
    }


}
