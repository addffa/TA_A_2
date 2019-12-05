package apap.tugas_akhir.siperpustakaan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {
    @JsonProperty("result")
    private UserDetail userDetail;

    @JsonProperty("result")
    private PengadaanDetail pengadaanDetail;

    @JsonProperty("status")
    private Integer status;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public PengadaanDetail getPengadaanDetail() {
        return pengadaanDetail;
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
}
