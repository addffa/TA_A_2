package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.repository.PengadaanDb;

import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PengadaanServiceImpl implements PengadaanService{

    @Autowired
    PengadaanDb pengadaanDb;

    @Override
    public void tambahPengadaan(PengadaanBukuModel pengadaanModel, UserModel userModel, PengadaanDetail pengadaanDetail){
        if(userModel.getRole().equals("Pustakawan")){
            pengadaanModel.setStatus(1);
        }
        else if(userModel.getRole().equals("Guru") && (pengadaanDetail.isIs_pengurus() == true && pengadaanDetail.getTotal_simpanan() > 1000000)){
            pengadaanModel.setStatus(3);
        }
        else if(userModel.getRole().equals("Guru") || userModel.getRole().equals("Siswa") ){
            pengadaanModel.setStatus(0);
        }
        pengadaanModel.setUser(userModel);
        pengadaanDb.save(pengadaanModel);
    }
}
