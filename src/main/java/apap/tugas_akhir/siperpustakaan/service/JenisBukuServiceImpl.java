package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.JenisBukuModel;
import apap.tugas_akhir.siperpustakaan.repository.JenisBukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JenisBukuServiceImpl implements JenisBukuService {
    @Autowired
    JenisBukuDb jenisBukuDb;

    @Override
    public List<JenisBukuModel> getJenisBukuList() {
        return jenisBukuDb.findAll();
    }
}
