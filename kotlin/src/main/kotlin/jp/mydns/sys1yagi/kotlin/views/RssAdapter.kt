package jp.mydns.sys1yagi.kotlin.views

import org.jetbrains.annotations.Nullable
import jp.mydns.sys1yagi.kotlin.tools.RDF
import jp.mydns.sys1yagi.kotlin.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

public open class RssAdapter(context: Context) : ArrayAdapter<RDF.Item>(context, -1) {
    public fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var view = convertView
        var holder: ViewHolder? = null
        val item = getItem(position)
        if (view == null) {
            view = View.inflate(getContext()!!, R.layout.listitem_rss, null)!!
            holder = ViewHolder(view)
            view.setTag(holder)
        }
        if (holder == null) {
            holder = view.getTag() as ViewHolder
        }

        holder?.title?.setText(item?.title)

        return view
    }

    public open class ViewHolder(val root: View){
        val title = root.findViewById(R.id.title_text) as TextView
    }
}
