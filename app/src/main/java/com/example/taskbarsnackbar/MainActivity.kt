package com.example.taskbarsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var listitems = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null
    lateinit var listView: ListView
    lateinit var fab:FloatingActionButton
    lateinit var undoOnClickListener: View.OnClickListener


    private var closed = false
    // Thankyou for the code Rajat Palankar at https://protocoderspoint.com/floating-action-button-animation-fab-menu-example/
    val rotateOpen : Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.rotateopen) }
    private  val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotateclose) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lv1)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listitems)

        listView.adapter = adapter
        fab = findViewById(R.id.FAB1)

        fab.setOnClickListener {
            //view ->
            addlistitem()
            Snackbar.make(it, "Added an item", Snackbar.LENGTH_LONG).setAction("Undo", undoOnClickListener).show()
            addAnimation(closed)
            closed = !closed;
        }
        undoOnClickListener = View.OnClickListener {
            listitems.removeAt(listitems.size -1)
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "Item removed.", Snackbar.LENGTH_LONG).setAction("Action", undoOnClickListener ).show()
        }

    }

    private fun addlistitem() {
        listitems.add("Item 1")
        adapter?.notifyDataSetChanged()
    }

    private fun addAnimation(closed:Boolean) {
        if(!closed){
            fab.startAnimation(rotateOpen)
        }else{
            fab.startAnimation(rotateClose)
        }
    }
}