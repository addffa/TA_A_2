package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.StatusModel;
import apap.tugas_akhir.siperpustakaan.repository.StatusDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusDb statusDb;

    public List<StatusModel> getStatusModelList(){
        return statusDb.findAll();
    }
}
