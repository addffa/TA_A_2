package apap.tugas_akhir.siperpustakaan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {
    @JsonProperty("result")
    private PegawaiDetail pegawaiDetail;

    @JsonSetter("status")
    private Integer status;

    public PegawaiDetail getPegawaiDetail() {
        return pegawaiDetail;
    }

    public void setPegawaiDetail(PegawaiDetail pegawaiDetail) {
        this.pegawaiDetail = pegawaiDetail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
