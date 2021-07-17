package com.descolab.chbpip.home

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig

import com.descolab.chbpip.service.response.Berita
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),BeritaAdapter.ListCategoryListener,HomeContract.View {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: HomePresenter? = null
    private var mAdapterBeritaAdapter: BeritaAdapter? = null
    val dataBerita: ArrayList<Berita> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)

        mActionListener = context?.let { HomePresenter(it, this) }
        mActionListener?.loadTopHeadline()
        setupRvCategory()
        setData()

        textView3.text = "Hallo! "+DataConfig.getString(DataConfig.NAME)
        textView4.text = "ID : "+DataConfig.getString(DataConfig.NO_ID)
        textView7.text = "Satker : "+DataConfig.getString(DataConfig.SATKER)
        Log.d("Cek", "ROLE ID " + DataConfig.getString(DataConfig.ROLE_ID))
        if(DataConfig.getString(DataConfig.ROLE_ID).equals("1")){
            view_bg.setBackgroundResource(R.drawable.ic_bg_header_non_mitra)
            view2.setBackgroundResource(R.drawable.benner_pancasila)
            Log.d("Cek", "USER = NON MITRA " + DataConfig.getString(DataConfig.ROLE_ID))
        }else{
            Log.d("Cek", "USER = MITRA " + DataConfig.getString(DataConfig.ROLE_ID))
            view_bg.setBackgroundResource(R.drawable.ic_bg_header_mitra)
            view2.setBackgroundResource(R.drawable.benner_saya_indonesia)
        }


        btnListDirektorat.setOnClickListener {
            if(DataConfig.getString(DataConfig.ROLE_ID).equals("1")){
                val listDirektorat = Intent(getActivity(), SplashDella1Activity::class.java)
                context!!.startActivity(listDirektorat)
            }else{
                val listDirektorat = Intent(getActivity(), SplashDella2Activity::class.java)
                context!!.startActivity(listDirektorat)
            }

        }
    }

    private fun setData() {
        val data = arrayListOf(
            Berita("1", "Kepala BPIP Pimpin Tahlil Kebangsaan Peringati Harlah Pancasila dan Ir. Soekarno",
                "Kepala Badan Pembinaan Ideologi Pancasila (BPIP) Republik Indonesia, Prof. Drs. K.H. Yudian Wahyudi, Ph.D. sampaikan bahwa sebagai masyarakat Indonesia, kita perlu untuk bersyukur. Menurut beliau, bangsa ini merupakan bangsa yang memiliki rasa persatuan tinggi.",
                "https://bpip.go.id/bpip/backend/files/content/berita/2021/06/06/7381004.jpeg"),
            Berita("2", "Media Sosial Jadi Salah Satu Platform Utama Pembinaan Ideologi Pancasila",
                "Jakarta:- Pada Rapat Dengar Pendapat yang digelar di ruang sidang Komisi II DPR RI, Kepala BPIP menyebutkan bahwa alokasi anggaran yang digunakan BPIP selama ini menyasar pada Pembinaan Ideologi Pancasila kepada masyarakat, salah satunya melalui ruang-ruang media sosial, Rabu (2/6).\n" +
                        "\n" +
                        "Profesor Yudian menambahkan jika ruang-ruang media sosial inilah yang nantinya akan diisi oleh para influencer dan youtuber yang menjunjung tinggi nilai-nilai Pancasila dengan menghadirkan konten-konten positif kepada masyarakat.",
                "https://bpip.go.id/bpip/backend/files/content/berita/2021/06/02/7311004.png"),
            Berita("3", "Pancasila Harus Menjadi Ideologi Hidup Dan Praksis",
                " Rumah Kebudayaan Nusantara (RKN) menyelenggarakan diskusi dengan tema Tantangan Ideologi Pancasila yang menghadirkan Politisi Partai Solidaritas Indonesia (PSI) Guntur Romli dan Staf Khusus Dewan Pengarah Badan Pembinaan Ideologi Pancasila (BPIP) Antonius Benny Susetyo.",
                "https://bpip.go.id/bpip/backend/files/content/berita/2021/06/05/737.jpg"),
            Berita("4", "Masyarakat Apresiasi BPIP Gelar Uapaca Harlah Pancasila Secara Virtual Kombinasi",
                "Jakarta:- Wabah COVID-19 yang masih belum berakhir menuntut kita untuk terus mematuhi protokol kesehatan sehingga terhindar dari ancaman virus yang membahayakan itu.\n" +
                        " \n" +
                        "Tidak hanya di lingkungan masyarakat, perkantoran, sarana ibadah bahkan hari-hari besar, seperti upacara peringatan Hari Lahir Pancasila yang dilaksanakan pada tanggal 1 juni 2021 harus dilakukan secara terpisah.",
                "https://bpip.go.id/bpip/backend/files/content/berita/2021/06/02/729.jpg"),
            Berita("5", "BPIP dan KSP Gelar Rakor Strategi Transformasi Manajemen Akuntabilitas Kinerja",
                "Jakarta:- Biro Perencanaan dan Keuangan Badan Pembinaan Ideologi Pancasila (BPIP) melaksanakan Rapat Koordinasi Strategi Transformasi Manajemen Akuntabilitas Kinerja Melalui Penandaan Output Strategis pada Sismonev Kantor Staf Presiden Senin, (5/4).\n" +
                        "\n" +
                        "Kepala Biro Perencanaan dan Keuangan BPIP Tonny Agung Arifianto, S.E., M.A.B mengatakan kegiatan tersebut bertujuan untuk melakukan konfirmasi dan arahan terkait Format 8 Kolom (F8K) SISMONEV KSP.",
                "https://bpip.go.id/bpip/backend/files/content/berita/2021/05/31/7261004.jpg"),

            )
        mAdapterBeritaAdapter = context?.let {
            BeritaAdapter(it, data, this)
        }
        rvBerita?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rvBerita?.setHasFixedSize(true)
        rvBerita?.adapter = mAdapterBeritaAdapter
    }

    private fun setupRvCategory() {


    }




    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }


    override fun toDetailCategory(item: Berita) {
    }


}