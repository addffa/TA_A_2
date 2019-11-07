package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BukuDb extends JpaRepository<BukuModel, Integer> {
<<<<<<< HEAD

    //Query untuk mencari buku berdasarkan Id buku
    Optional<BukuModel> findById(Integer id);
=======
    Optional<BukuModel> findById(Integer idBuku);
>>>>>>> e1f257cd6f981e2b71aba2e4a6278e581108f070
}
