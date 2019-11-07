package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.repository.BukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class BukuServiceImpl implements BukuService {
    @Autowired
    private BukuDb bukuDb;

    @Override
    public BukuModel findBukuById(Integer id) {
        Optional<BukuModel> buku = bukuDb.findById(id);
        if(buku.isPresent()) {
            return buku.get();
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<BukuModel> getListBuku() {
        return bukuDb.findAll();
    }
}
