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
import com.kc.madridshops.activity.ActivityDetailActivity
import com.kc.madridshops.adapter.ActivityRecyclerViewAdapter
import com.kc.madridshops.domain.model.Activities


class ActivityListFragment : Fragment() {

    private lateinit var root: View
    private lateinit var activityList: RecyclerView
    //private val adapter: ShopRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_list, container, false)
            activityList = root.findViewById(R.id.fragment_list_recycler_view)
            activityList.layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.recycler_columns))
            activityList.itemAnimator = DefaultItemAnimator()
        }

        return root

    }


    fun activitiesFromActivityActivity(activities: Activities){
        val adapter = ActivityRecyclerViewAdapter(activities)
        activityList.adapter = adapter
        // When one item of recycler view is pressed
        adapter.onClickListener = View.OnClickListener {view: View ->
            val position = activityList.getChildAdapterPosition(view)
            //Log.d("Posicion","Posicion: " + position)
            val activityToShow = activities.get(position)

            // Start Activity
            startActivity(ActivityDetailActivity.intent(activity, activityToShow))
        }
    }


}// Required empty public constructor
