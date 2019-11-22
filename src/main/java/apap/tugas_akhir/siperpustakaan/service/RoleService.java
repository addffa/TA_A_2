package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> getListRole();
    RoleModel getRoleByNama(String nama);
    void addRole(RoleModel roleModel);
}
