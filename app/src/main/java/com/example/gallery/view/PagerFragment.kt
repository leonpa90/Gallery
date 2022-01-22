package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.gallery.R
import com.example.gallery.adapter.PageDapater
import com.example.gallery.model.Photo
import com.example.gallery.viewModel.PageViewModel
import kotlinx.android.synthetic.main.pager_fragment.*

class PagerFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pager_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list: MutableList<Photo>? =
            (arguments?.getSerializable("photos") as? MutableList<Photo>)
        list?.let {
            val pageAdapter = PageDapater(this, it)
            pager_view.adapter = pageAdapter
            arguments?.getInt("photoposition")?.let {
                view.post { pager_view.currentItem = it }
            }
            val pageItem: PageViewModel? = ViewModelProvider(this).get(
                PageViewModel::class.java
            )
            pager_view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)


                        if(list.get(position).preferiti)
                        {
                            addPrefBtn.text="Rimuovi dai preferiti"

                        }
                    else

                            addPrefBtn.text="Aggiungi ai preferiti"


                }
            })
            addPrefBtn.setOnClickListener { view ->

                if(list.get(pager_view.currentItem).preferiti)
                {
                    pageItem?.updatePref(requireContext(),list.get(pager_view.currentItem))
                    addPrefBtn.text="Aggiungi ai preferiti"
                Toast.makeText(context, "Rimosso dai preferiti", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    pageItem?.inserisciPref(requireContext(), it.get(pager_view.currentItem))
                    addPrefBtn.text="Rimuovi dai preferiti"
                    Toast.makeText(context, "Aggiunto ai preferiti", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}