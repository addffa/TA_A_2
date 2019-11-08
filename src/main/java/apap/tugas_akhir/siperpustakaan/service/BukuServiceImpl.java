package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.repository.BukuDb;
import apap.tugas_akhir.siperpustakaan.repository.PeminjamanBukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BukuServiceImpl implements BukuService {


    @Autowired
    BukuDb bukuDb;

    @Autowired
    PeminjamanBukuDb peminjamanBukuDb;

    @Override
    public void tambahBuku(BukuModel bukuModel) {
        bukuDb.save(bukuModel);
    }

    @Override
    public List<BukuModel> getBukuList() {
        return bukuDb.findAll();
    }
  
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

    public List<BukuModel> getListBuku() {
        return bukuDb.findAll();
    }

    @Override
    public int jumlahBukuDipinjam(BukuModel buku) {
        return peminjamanBukuDb.countPeminjamanBukuModelByBukuAndStatusNot(buku, 4);
    }
}
