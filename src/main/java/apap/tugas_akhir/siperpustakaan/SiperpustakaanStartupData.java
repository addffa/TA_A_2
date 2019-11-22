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
            roleService.addRole(new RoleModel("Kepala Sekolah"));
        }

        if(roleService.getRoleByNama("Admin TU") == null) {
            roleService.addRole(new RoleModel("Admin TU"));
        }

        if(roleService.getRoleByNama("Guru") == null) {
            roleService.addRole(new RoleModel("Guru"));
        }

        if(roleService.getRoleByNama("Siswa") == null) {
            roleService.addRole(new RoleModel("Siswa"));
        }

        if(roleService.getRoleByNama("Pustakawan") == null) {
            roleService.addRole(new RoleModel("Pustakawan"));
        }

        if(roleService.getRoleByNama("Pengurus Koperasi") == null) {
            roleService.addRole(new RoleModel("Pengurus Koperasi"));
        }

        if(roleService.getRoleByNama("Anggota Koperasi") == null) {
            roleService.addRole(new RoleModel("Anggota Koperasi"));
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

        UserModel user;
        if(!userService.isUsernameExists("guru")) {
            user = new UserModel();
            user.setUsername("guru");
            user.setPassword("guru");
            user.setRole(roleService.getRoleByNama("Guru"));
            userService.addUser(user);
        }

        if(!userService.isUsernameExists("siswa")) {
            user = new UserModel();
            user.setUsername("siswa");
            user.setPassword("siswa");
            user.setRole(roleService.getRoleByNama("Siswa"));
            userService.addUser(user);
        }

        if(!userService.isUsernameExists("anggota")) {
            user = new UserModel();
            user.setUsername("anggota");
            user.setPassword("anggota");
            user.setRole(roleService.getRoleByNama("Anggota Koperasi"));
            userService.addUser(user);
        }
    }
}
