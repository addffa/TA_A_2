package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeminjamanBukuDb extends JpaRepository<PeminjamanBukuModel, Integer> {
    @Query("SELECT COUNT(pb) " +
            "FROM PeminjamanBukuModel pb " +
            "WHERE pb.buku=:buku " +
            "AND NOT (pb.status=:status1 or pb.status=:status2)")
    int jumlahBukuDipinjam(@Param("buku") BukuModel buku,
                           @Param("status1") int status1,
                           @Param("status2") int status2);

    List<PeminjamanBukuModel> findByUser(UserModel user);
}
