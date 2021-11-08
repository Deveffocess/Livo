package com.livo.nuo.lib.scrollimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.livo.nuo.R

import com.nostra13.universalimageloader.core.ImageLoader

class CommonFragment : Fragment(), DragLayout.GotoDetailListener {
    private var imageView: ImageView? = null
    private var imageUrl: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_common, null)
        val dragLayout = rootView.findViewById<View>(R.id.drag_layout) as DragLayout
        imageView = dragLayout.findViewById<View>(R.id.img_slider) as ImageView
        ImageLoader.getInstance().displayImage(imageUrl, imageView!!)
        dragLayout.setGotoDetailListener(this)
        return rootView
    }

    override fun gotoDetail() {}

    fun bindData(imageUrl: String) {
        this.imageUrl = imageUrl
    }
}
