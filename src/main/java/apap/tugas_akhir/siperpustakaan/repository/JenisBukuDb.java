package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.JenisBukuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisBukuDb extends JpaRepository<JenisBukuModel, Integer> {

}
