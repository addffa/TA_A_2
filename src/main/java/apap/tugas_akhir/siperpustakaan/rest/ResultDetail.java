package apap.tugas_akhir.siperpustakaan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {

    public PegawaiDetail getPegawaiDetail() {
        return pegawaiDetail;
    }

    public void setPegawaiDetail(PegawaiDetail pegawaiDetail) {
        this.pegawaiDetail = pegawaiDetail;
    }

    @JsonProperty("result")
    private PegawaiDetail pegawaiDetail;

}
