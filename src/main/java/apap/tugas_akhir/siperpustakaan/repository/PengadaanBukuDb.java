package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengadaanBukuDb extends JpaRepository<PengadaanBukuModel, Integer> {
    List<PengadaanBukuModel> findByUser(UserModel user);
}
