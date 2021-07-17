package com.descolab.chbpip.service.response

import com.google.gson.annotations.SerializedName

data class DataItem(

        @field:SerializedName("nama")
        val nama: String? = null,

        @field:SerializedName("updated_at")
        val updatedAt: Any? = null,

        @field:SerializedName("created_at")
        val createdAt: Any? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("deskripsi")
        val deskripsi: String? = null,

        @field:SerializedName("pesan")
        val pesan: String? = null,

        @field:SerializedName("kerjasama")
        val kerjasama: String? = null,


        @field:SerializedName("unit_satker")
        val unitSatker: String? = null,


        @field:SerializedName("id_user")
        val idUser: String? = null,

        @field:SerializedName("tanggal")
        val tanggal: String? = null,

        @field:SerializedName("perihal")
        val perihal: String? = null,

        @field:SerializedName("asal_pengusul")
        val asalPengusul: String? = null,

        @field:SerializedName("file")
        val file: String? = null,

        @field:SerializedName("nama_file")
        val namaFile: String? = null,

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("from")
        val from: String? = null,

        @field:SerializedName("to")
        val to: String? = null,

        @field:SerializedName("is_seen")
        val isSeen: String? = null,

        @field:SerializedName("isi")
        val isi: String? = null,

        @field:SerializedName("nama_lembaga")
        val namaLembaga: String? = null,

        @field:SerializedName("nama_sublembaga")
        val namaSubLembaga: String? = null,

        @field:SerializedName("id_lembaga")
        val idLembaga: String? = null,

        //form luring
        @field:SerializedName("tema")
        val tema: String? = null,
        @field:SerializedName("jam")
        val jam: String? = null,

        @field:SerializedName("jenisPertemuan")
        val jenisPertemuan: String? = null,

        @field:SerializedName("tipe_pengajuan")
        val tipePengajuan: String? = null,

        @field:SerializedName("nama_status")
        val namaStatus: String? = null,

        @field:SerializedName("nama_direktorat")
        val namaDirektorat: String? = null,

        @field:SerializedName("id_direktorat")
        val idDirektorat: String? = null,





)