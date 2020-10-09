package com.bram.vsgasqlite;

public class GetSet {
    String nama_buku;
    Integer jumlah;

    public GetSet(String nama_buku, Integer jumlah) {
        this.nama_buku = nama_buku;
        this.jumlah = jumlah;
    }

    public String getNama_buku() {
        return nama_buku;
    }

    public void setNama_buku(String nama_buku) {
        this.nama_buku = nama_buku;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
