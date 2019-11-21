package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.StatusPinjamanModel;
import apap.tugas_akhir.siperpustakaan.repository.StatusDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusPinjamanServiceImpl implements StatusPinjamanService {
    @Autowired
    StatusDb statusDb;

    public List<StatusPinjamanModel> getStatusModelList(){
        return statusDb.findAll();
    }
}
