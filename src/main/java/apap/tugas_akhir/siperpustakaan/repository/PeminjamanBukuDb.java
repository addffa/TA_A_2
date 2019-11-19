package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanBukuDb extends JpaRepository<PeminjamanBukuModel, Integer> {
    int countPeminjamanBukuModelByBukuAndStatus(BukuModel buku, int status);
}
