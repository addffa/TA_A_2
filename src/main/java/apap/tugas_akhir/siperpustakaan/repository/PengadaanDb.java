package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PengadaanDb extends JpaRepository<PengadaanBukuModel, Integer>{
    //Query untuk mencari buku berdasarkan Id buku
    Optional<PengadaanBukuModel> findById(Integer idBuku);

}
