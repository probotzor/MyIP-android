package com.probotzor.milosjakovljevic.myip

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.github.salomonbrys.kotson.fromJson
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import org.jetbrains.anko.toast



//version with logo

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView


    data class IpBody(val ip: String?)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this, "ca-app-pub-6707325058220162~3771538566")
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        fetch()



        refreshButton.setOnClickListener {

               fetch()

            }
        }

    fun fetch() {
        Fuel.get("https://api.ipify.org/?format=json").responseString { request, response, result ->
            println(response)
            result.fold({ data ->
                println(data)
                val ipStr = Gson().fromJson<IpBody>(data).ip
                println(ipStr)
                whatIP.text = "My IP is:"
                ipLabel.text = ipStr
                toast("Updated")

            }, { err ->
                whatIP.text = "No internet access!"
                ipLabel.text = "Unable to fetch ip!"
                toast("Cannot update")
            })


        }
    }


    }





