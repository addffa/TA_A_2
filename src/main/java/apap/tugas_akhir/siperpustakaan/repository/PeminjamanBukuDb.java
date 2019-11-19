package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanBukuDb extends JpaRepository<PeminjamanBukuModel, Integer> {
    @Query("SELECT COUNT(pb) FROM PeminjamanBuku pb WHERE pb.idBuku=:idBuku and not(pb.status=:status1 or pb.status=:status2)")
    int jumlahBukuDipinjam(int idBuku, int status1, int status2);
}
