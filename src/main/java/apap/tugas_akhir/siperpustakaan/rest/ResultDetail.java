package apap.tugas_akhir.siperpustakaan.rest;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {
    @JsonProperty("result")
    private UserDetail userDetail;

    @JsonProperty("result")
    private AnggotaKoperasiDetail anggotaKoperasiDetail;

    @JsonProperty("result")
    private PengadaanDetail pengadaanDetail;

    @JsonProperty("status")
    private Integer status;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public AnggotaKoperasiDetail getAnggotaKoperasiDetail() {
        return anggotaKoperasiDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAnggotaKoperasiDetail(AnggotaKoperasiDetail anggotaKoperasiDetail) {
        this.anggotaKoperasiDetail = anggotaKoperasiDetail;
    }

    public PengadaanDetail getPengadaanDetail() {
        return pengadaanDetail;
    }

    public void setPengadaanDetail(PengadaanDetail pengadaanDetail) {
        this.pengadaanDetail = pengadaanDetail;
    }
}
