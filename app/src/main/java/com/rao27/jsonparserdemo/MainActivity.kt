package com.rao27.jsonparserdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rao27.jsonparserdemo.model.Photo
import com.rao27.jsonparserdemo.retrofit.GetDataService
import com.rao27.jsonparserdemo.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.DividerItemDecoration



class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val service = RetrofitClient.getRetrofitInstance()?.create(GetDataService::class.java)
        val call = service?.getAllPhotos()
        call?.enqueue(object : Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity,"Error while loading data... Please try again later",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                progressBar.visibility = View.GONE
                populateData(response.body())
            }

        })
    }

    private fun populateData(list: List<Photo>?) {
        list?.also { photoList->
            adapter = PhotoAdapter(photoList)
            val layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context,
                layoutManager.orientation
            )
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.visibility = View.VISIBLE
        }?: Toast.makeText(this@MainActivity, "Got empty list.", Toast.LENGTH_SHORT).show()
    }
}
