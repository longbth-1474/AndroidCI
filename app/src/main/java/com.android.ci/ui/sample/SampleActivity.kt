package com.android.ci.ui.sample

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ci.BR
import com.android.ci.R
import com.android.ci.databinding.ActivitySampleBinding
import com.android.ci.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sample.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Sample activity
 * Show list post got from URL_END_POINT = https://jsonplaceholder.typicode.com/
 */
class SampleActivity : BaseActivity<ActivitySampleBinding, SampleViewModel>() {
    override val viewModel: SampleViewModel by viewModel()
    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.activity_sample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        val adapter = SampleAdapter {
            Toast.makeText(this@SampleActivity, "${it.title}", Toast.LENGTH_SHORT).show()
        }
        rvPosts?.apply{
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
        }

        viewModel.posts.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }
}
