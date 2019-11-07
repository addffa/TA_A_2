package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BukuDb extends JpaRepository<BukuModel, Integer> {

    //Query untuk mencari buku berdasarkan Id buku
    Optional<BukuModel> findById(Integer idBuku);
}
