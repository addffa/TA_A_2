package apap.tugas_akhir.siperpustakaan.repository;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDb extends JpaRepository<UserModel, String> {
    boolean existsUserModelsByUsername(String username);

    UserModel findByUsername(String username);
}
