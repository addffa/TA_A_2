package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.repository.BukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BukuServiceImpl implements BukuService {


    @Autowired
    BukuDb bukuDb;

    @Override
    public void tambahBuku(BukuModel bukuModel) {
        bukuDb.save(bukuModel);
    }

    @Override
    public Optional<BukuModel> getBukuById(Integer idBuku) {
        return bukuDb.findById(idBuku);
    }

    @Override
    public BukuModel ubahJumlahBuku(BukuModel bukuModel) {
        BukuModel targetBuku = bukuDb.findById(bukuModel.getId()).get();
        targetBuku.setJumlah(bukuModel.getJumlah());
        bukuDb.save(targetBuku);
        return targetBuku;
    }

    @Override
    public void hapusBuku(Integer idBuku){
        BukuModel buku = getBukuById(idBuku).get();
        bukuDb.delete(buku);
    }



}
