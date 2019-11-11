package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDb extends JpaRepository<RoleModel, Integer> {
}
