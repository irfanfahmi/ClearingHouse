package com.descolab.chbpip.media

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.descolab.chbpip.R
import kotlinx.android.synthetic.main.fragment_media.*


class MediaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivInstagram.setOnClickListener {
            val uri: Uri = Uri.parse("https://www.instagram.com/bpipri")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.instagram.android")
            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/bpipri")
                    )
                )
            }


        }
        ivTwitter.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("twitter://user?screen_name=bpipri")
                    )
                )
            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/#!/bpipri")
                    )
                )
            }
        }
        ivFacebook.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("fb://page/AEG62qBRbMqgVjb_XKO1Ld5")
                    )
                )
            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/BadanPembinaanIdeologiPancasila/")
                    )
                )
            }
        }
        ivLabelMitra.setOnClickListener {
            activity?.onBackPressed()
        }
    }


}