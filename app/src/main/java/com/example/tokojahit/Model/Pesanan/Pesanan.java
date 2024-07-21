package com.example.tokojahit.Model.Pesanan;

import com.google.gson.annotations.SerializedName;

public class Pesanan {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("date")
    private String date;
    @SerializedName("image")
    private String image;
    @SerializedName("baju")
    private String baju;

    @SerializedName("kain")
    private String kain;

    @SerializedName("desain")
    private String desain;

    @SerializedName("lingkar_badan")
    private String lingkarBadan;

    @SerializedName("lingkar_pinggang")
    private String lingkarPinggang;

    @SerializedName("panjang_dada")
    private String panjangDada;

    @SerializedName("lebar_dada")
    private String lebarDada;

    @SerializedName("panjang_punggung")
    private String panjangPunggung;

    @SerializedName("lebar_punggung")
    private String lebarPunggung;

    @SerializedName("lebar_bahu")
    private String lebarBahu;

    @SerializedName("lingkar_leher")
    private String lingkarLeher;

    @SerializedName("tinggi_dada")
    private String tinggiDada;

    @SerializedName("jarak_dada")
    private String jarakDada;

    @SerializedName("lingkar_pangkal_lengan")
    private String lingkarPangkalLengan;

    @SerializedName("panjang_lengan")
    private String panjangLengan;

    @SerializedName("lingkar_siku")
    private String lingkarSiku;

    @SerializedName("lingkar_pergelangan_tangan")
    private String lingkarPergelanganTangan;

    @SerializedName("lingkar_kerung_lengan")
    private String lingkarKerungLengan;

    @SerializedName("lingkar_panggul_1")
    private String lingkarPanggul1;

    @SerializedName("lingkar_panggul_2")
    private String lingkarPanggul2;

    @SerializedName("lingkar_rok")
    private String lingkarRok;
    @SerializedName("proses")
    private String proses;

    public Pesanan(){}

    public Pesanan(String id, String name, String price, String date, String image, String baju, String kain, String desain,String lingkarBadan, String lingkarPinggang,String panjangDada, String lebarDada, String panjangPunggung, String lebarPunggung, String lebarBahu, String lingkarLeher, String tinggiDada, String jarakDada, String lingkarPangkalLengan, String panjangLengan, String lingkarSiku, String lingkarPergelanganTangan, String lingkarKerungLengan, String lingkarPanggul1, String lingkarPanggul2, String lingkarRok, String proses) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.image = image;
        this.baju = baju;
        this.kain = kain;
        this.desain = desain;
        this.lingkarBadan = lingkarBadan;
        this.lingkarPinggang = lingkarPinggang;
        this.panjangDada = panjangDada;
        this.lebarDada = lebarDada;
        this.panjangPunggung = panjangPunggung;
        this.lebarPunggung = lebarPunggung;
        this.lebarBahu = lebarBahu;
        this.lingkarLeher = lingkarLeher;
        this.tinggiDada = tinggiDada;
        this.jarakDada = jarakDada;
        this.lingkarPangkalLengan = lingkarPangkalLengan;
        this.panjangLengan = panjangLengan;
        this.lingkarSiku = lingkarSiku;
        this.lingkarPergelanganTangan = lingkarPergelanganTangan;
        this.lingkarKerungLengan = lingkarKerungLengan;
        this.lingkarPanggul1 = lingkarPanggul1;
        this.lingkarPanggul2 = lingkarPanggul2;
        this.lingkarRok = lingkarRok;
        this.proses = proses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBaju() {
        return baju;
    }

    public String getDesain() {
        return desain;
    }

    public String getKain() {
        return kain;
    }

    public String getLingkarBadan() {
        return lingkarBadan;
    }

    public String getLingkarPinggang() {
        return lingkarPinggang;
    }

    public String getPanjangDada() {
        return panjangDada;
    }

    public String getLebarDada() {
        return lebarDada;
    }

    public String getPanjangPunggung() {
        return panjangPunggung;
    }

    public String getLebarPunggung() {
        return lebarPunggung;
    }

    public String getLebarBahu() {
        return lebarBahu;
    }

    public String getLingkarLeher() {
        return lingkarLeher;
    }

    public String getTinggiDada() {
        return tinggiDada;
    }

    public String getJarakDada() {
        return jarakDada;
    }

    public String getLingkarPangkalLengan() {
        return lingkarPangkalLengan;
    }

    public String getPanjangLengan() {
        return panjangLengan;
    }

    public String getLingkarSiku() {
        return lingkarSiku;
    }

    public String getLingkarPergelanganTangan() {
        return lingkarPergelanganTangan;
    }

    public String getLingkarKerungLengan() {
        return lingkarKerungLengan;
    }

    public String getLingkarPanggul1() {
        return lingkarPanggul1;
    }

    public String getLingkarPanggul2() {
        return lingkarPanggul2;
    }

    public String getLingkarRok() {
        return lingkarRok;
    }
    public String getProses() {
        return proses;
    }

}
