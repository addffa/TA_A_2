package apap.tugas_akhir.siperpustakaan;

import apap.tugas_akhir.siperpustakaan.model.RoleModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.service.RoleService;
import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SiperpustakaanStartupData implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(roleService.getRoleByNama("Kepala Sekolah") == null) {
            roleService.addRole(new RoleModel(1, "Kepala Sekolah"));
        }

        if(roleService.getRoleByNama("Admin TU") == null) {
            roleService.addRole(new RoleModel(2, "Admin TU"));
        }

        if(roleService.getRoleByNama("Guru") == null) {
            roleService.addRole(new RoleModel(3, "Guru"));
        }

        if(roleService.getRoleByNama("Siswa") == null) {
            roleService.addRole(new RoleModel(4, "Siswa"));
        }

        if(roleService.getRoleByNama("Pustakawan") == null) {
            roleService.addRole(new RoleModel(5, "Pustakawan"));
        }

        if(roleService.getRoleByNama("Pengurus Koperasi") == null) {
            roleService.addRole(new RoleModel(6, "Pengurus Koperasi"));
        }

        if(roleService.getRoleByNama("Anggota Koperasi") == null) {
            roleService.addRole(new RoleModel(7, "Anggota Koperasi"));
        }

        UserModel admin;
        if(userService.isUsernameExists("admin")) {
            admin = userService.getByUsername("admin");
        } else {
            admin = new UserModel();
            admin.setUsername("admin");
        }
        admin.setPassword("admin");
        admin.setRole(roleService.getRoleByNama("Pustakawan"));
        userService.addUser(admin);
    }
}
