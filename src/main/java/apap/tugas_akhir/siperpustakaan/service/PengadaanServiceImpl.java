package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.repository.PengadaanBukuDb;

import apap.tugas_akhir.siperpustakaan.rest.AnggotaKoperasiDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PengadaanServiceImpl implements PengadaanService{

    @Autowired
    PengadaanBukuDb pengadaanBukuDb;

    @Override
    public void tambahPengadaan(PengadaanBukuModel pengadaanModel, UserModel userModel, AnggotaKoperasiDetail anggotaKoperasiDetail){
        if(userModel.getRole().getNama().equals("Pustakawan")){
            pengadaanModel.setStatus(1);
        }
        else if(userModel.getRole().getNama().equals("Guru") && (anggotaKoperasiDetail != null && anggotaKoperasiDetail.isIs_pengurus() == true && anggotaKoperasiDetail.getTotal_simpanan() > 1000000)){
            pengadaanModel.setStatus(3);
        }
        else if(userModel.getRole().getNama().equals("Guru") || userModel.getRole().getNama().equals("Siswa") ){
            pengadaanModel.setStatus(0);
        }
        pengadaanModel.setUser(userModel);
        pengadaanBukuDb.save(pengadaanModel);
    }

    @Override
    public List<PengadaanBukuModel> getListPengadaanBuku() {
        return pengadaanBukuDb.findAll();
    }

    @Override
    public List<PengadaanBukuModel> getListPengadaanBukuByUser(UserModel user) {
        return pengadaanBukuDb.findByUser(user);
    }

    @Override
    public PengadaanBukuModel getPengadaanById(int idPengadaan) {
        return pengadaanBukuDb.findById(idPengadaan).get();
    }

    @Override
    public boolean hapusPengadaan(int idPengadaan, String role) {
        PengadaanBukuModel pengadaan = getPengadaanById(idPengadaan);
        if((role.equals("Pustakawan") && pengadaan.getStatus() < 2)
                || ((role.equals("Guru") || role.equals("Siswa")) && pengadaan.getStatus() < 1) ) {
            pengadaanBukuDb.delete(getPengadaanById(idPengadaan));
            return true;
        } else {
            return false;
        }
    }
}
