package jp.mydns.sys1yagi.kotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ListView

import roboguice.fragment.RoboFragment
import roboguice.inject.InjectView

import jp.mydns.sys1yagi.kotlin.R
import jp.mydns.sys1yagi.kotlin.tools.RDF;
import jp.mydns.sys1yagi.kotlin.views.RssAdapter;
import android.widget.ArrayAdapter
import android.content.Context
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import android.widget.Adapter
import android.content.Intent
import android.net.Uri

public open class RssFragment() : RoboFragment() {
    [InjectView(R.id.item_list)] var mListView: ListView? = null

    private var mUrl: String? = null

    public fun onCreate(savedInstanceState: Bundle): Unit {
        super.onCreate(savedInstanceState)
        if (getArguments() != null)
        {
            mUrl = getArguments()?.getString(ARG_URL)
        }
    }

    public fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View? {
        return inflater.inflate(R.layout.fragment_rss, container, false)
    }

    public fun onActivityCreated(savedInstanceState: Bundle): Unit {
        super.onActivityCreated(savedInstanceState)
        RDF.load(mUrl, { items ->
            getActivity()?.runOnUiThread(Runnable {
                run {
                    val adapter = RssAdapter(getActivity() as Context)
                    for (item in items) {
                        adapter.add(item)
                    }
                    mListView?.setAdapter(adapter)
                    mListView?.setOnItemClickListener { parent, view, position, id ->
                        val item = parent?.getItemAtPosition(position) as RDF.Item
                        Intent(Intent.ACTION_VIEW, Uri.parse(item.link!!)).startActivity(getActivity())
                    }
                }
            })
        })
    }

    class object {
        private val ARG_URL: String = "url"
        public open fun newInstance(url: String): RssFragment {
            val fragment = RssFragment()
            val args = Bundle()
            args.putString(ARG_URL, url)
            fragment.setArguments(args)
            return fragment
        }
    }
}
