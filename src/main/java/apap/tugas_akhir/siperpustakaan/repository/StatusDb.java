package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.StatusPinjamanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusDb extends JpaRepository<StatusPinjamanModel, Integer> {

    //Query untuk mencari buku berdasarkan Id buku
    Optional<StatusPinjamanModel> findById(Integer idStatus);
}