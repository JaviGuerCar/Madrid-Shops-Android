package com.kc.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kc.madridshops.R
import com.kc.madridshops.adapter.ShopRecyclerViewAdapter
import com.kc.madridshops.domain.model.Shops


class ShopListFragment : Fragment() {

    private lateinit var root: View
    private lateinit var shopList: RecyclerView
    private  var adapter: ShopRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_list, container, false)
            shopList = root.findViewById(R.id.fragment_list_recycler_view)
            shopList.layoutManager = GridLayoutManager(activity,1)
            shopList.itemAnimator = DefaultItemAnimator()
        }

        return root

    }


    fun shopsFromShopActivity(shops: Shops){
        adapter = ShopRecyclerViewAdapter(shops)
        shopList.adapter = adapter
    }


}// Required empty public constructor
