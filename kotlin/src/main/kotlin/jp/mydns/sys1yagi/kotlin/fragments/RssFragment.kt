package jp.mydns.sys1yagi.kotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.mydns.sys1yagi.kotlin.R
import roboguice.fragment.RoboFragment
import roboguice.inject.InjectView

public open class RssFragment() : RoboFragment() {
    [InjectView(R.id.text)] var mTextView: TextView? = null

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
        mTextView?.setText("sogeeee")
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
