package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusDb extends JpaRepository<StatusModel, Integer> {

    //Query untuk mencari buku berdasarkan Id buku
    Optional<StatusModel> findById(Integer idStatus);
}