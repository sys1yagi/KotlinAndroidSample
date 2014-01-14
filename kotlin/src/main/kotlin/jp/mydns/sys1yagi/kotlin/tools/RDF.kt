package jp.mydns.sys1yagi.kotlin.tools

import kotlin.dom.*
import java.util.List
import java.util.ArrayList
import java.net.HttpURLConnection
import java.net.URL
import java.io.InputStreamReader
import java.io.InputStream
import java.io.BufferedReader
import org.w3c.dom.Node
import org.w3c.dom.Element

object RDF{
    public data class Item(
            var title: String? = null,
            var link: String? = null,
            var description: String? = null,
            var content: String? = null,
            var date: String? = null
    )
    fun load(url: String?, listener: (List<Item>) -> Unit): Unit {
        Thread() {
            run {
                val document = parseXml(URL(url).openConnection()?.getInputStream() as InputStream)

                val items = document.getElementsByTagName("item")!!
                val list = ArrayList<Item>()
                for (itemNode in items.toList()) {
                    val item = createItem(itemNode as Element)
                    if (item != null) {
                        list.add(item)
                    }
                }

                listener(list as List<Item>)
            }
        }.start()
    }

    private fun createItem(item: Element): Item? {
        val rss = Item()

        rss.title = item.getElementsByTagName("title")?.first?.text
        rss.link = item.getElementsByTagName("link")?.first?.text
        rss.description = item.getElementsByTagName("description")?.first?.text
        rss.content = item.getElementsByTagName("content:encoded")?.first?.text
        rss.date = item.getElementsByTagName("dc:date")?.first?.text

        return rss
    }
}