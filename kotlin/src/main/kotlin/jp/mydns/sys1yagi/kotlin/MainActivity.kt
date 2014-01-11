package jp.mydns.sys1yagi.kotlin

import android.support.v4.app.Fragment
import android.os.Bundle
import jp.mydns.sys1yagi.kotlin.fragments.RssFragment
import roboguice.activity.RoboFragmentActivity

public open class MainActivity() : RoboFragmentActivity() {
    protected open fun onCreate(savedInstanceState: Bundle): Unit {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    RssFragment.newInstance("http://rss.asahi.com/rss/asahi/science.rdf") as Fragment
            ).commit()
        }
    }
}
