package com.android.ci.ui.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.android.ci.R
import com.android.ci.data.model.Post
import com.android.ci.databinding.AdapterItemPostBinding
import com.android.ci.ui.base.BaseRecyclerAdapter

class SampleAdapter(private val callback: ((Post) -> Unit)?) :
    BaseRecyclerAdapter<Post>(callBack = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.body == newItem.body
    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding =
        DataBindingUtil.inflate<AdapterItemPostBinding>(
            LayoutInflater.from(parent.context), R.layout.adapter_item_post, parent, false
        ).apply {
            root.setOnClickListener {
                this.post?.let { item ->
                    callback?.invoke(item)
                }
            }
        }

    override fun bind(binding: ViewDataBinding, item: Post) {
        if (binding is AdapterItemPostBinding) binding.post = item
    }
}
