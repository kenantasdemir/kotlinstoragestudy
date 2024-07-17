package com.kenant42.kotlinstoragestudy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenant42.kotlinstoragestudy.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWrite.setOnClickListener {
            writeExternal()
        }

        binding.btnRead.setOnClickListener {
            readExternal()
        }

        binding.btnDelete.setOnClickListener {
            deleteExternal()
        }
    }


    fun writeExternal() {
        try {
            val path = applicationContext.getExternalFilesDir(null)?.absolutePath
            val file = File(path, "file.txt")
            if (!file.exists()) {
                file.createNewFile()
            }

            val fw = FileWriter(file)
            val bw = BufferedWriter(fw)

            bw.write(binding.editTextText.text.toString())
            bw.flush()
            bw.close()

            fw.close()
            binding.editTextText.setText("")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readExternal() {
        try {
            val path = applicationContext.getExternalFilesDir(null)?.absolutePath
            val file = File(path, "file.txt")

            val fw = FileReader(file)
            val br = BufferedReader(fw)

            val sb = StringBuilder()
            var row: String? = null

            while ({ row = br.readLine();row }() != null) {
                sb.append(row)
            }

            br.close()
            binding.editTextText.setText("")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteExternal() {
        val path = applicationContext.getExternalFilesDir(null)?.absolutePath
        val file = File(path, "file.txt")
        file.delete()

    }


    fun writeInternal() {

        try {
            val fo = openFileOutput("file.txt", Context.MODE_PRIVATE)
            val writer = OutputStreamWriter(fo)
            writer.write(binding.editTextText.text.toString())
            writer.close()
            binding.editTextText.setText("")
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun readInternal() {
       try {
           val fi = openFileInput("file.txt")
           val isr = InputStreamReader(fi)
           val reader = BufferedReader(isr)
           val sb = StringBuilder()
           var row: String? = null

           while ({ row = reader.readLine();row }() != null) {
               sb.append(row + "\n")
           }

           reader.close()
           binding.editTextText.setText(sb.toString())
       }catch (e:Exception){
           e.printStackTrace()
       }

    }

    fun deleteInternal() {
        val path = filesDir
        val file = File(path, "file.txt")
        file.delete()
    }
}