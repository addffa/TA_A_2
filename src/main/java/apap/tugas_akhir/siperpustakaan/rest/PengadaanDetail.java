package apap.tugas_akhir.siperpustakaan.rest;

public class PengadaanDetail {

    private String nama;

    private String nia;

    private int total_simpanan;

    private int id_anggota;

    private boolean is_pengurus;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    public int getTotal_simpanan() {
        return total_simpanan;
    }

    public void setTotal_simpanan(int total_simpanan) {
        this.total_simpanan = total_simpanan;
    }

    public int getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(int id_anggota) {
        this.id_anggota = id_anggota;
    }

    public boolean isIs_pengurus() {
        return is_pengurus;
    }

    public void setIs_pengurus(boolean is_pengurus) {
        this.is_pengurus = is_pengurus;
    }
}
