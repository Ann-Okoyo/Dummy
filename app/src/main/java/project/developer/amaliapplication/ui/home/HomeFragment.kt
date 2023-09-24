package com.amali.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.amali.MainActivity
import com.amali.R
import com.amali.custom.CustomDialog
import com.amali.databinding.FragmentHomeBinding
import com.amali.extensions.gone
import com.amali.extensions.snackBarError
import com.amali.extensions.visible
import com.amali.model.VideoItem
import com.amali.model.VideoModel

class HomeFragment : Fragment() {

    //MARK: Variable declaration
    private lateinit var mContext: Context
    private var binding: FragmentHomeBinding? = null
    private lateinit var mainActivity: MainActivity
    private var dialog: CustomDialog? = null
    private var homeAdapter: HomePagerAdapter? = null
    private var arrayList: ArrayList<VideoItem?> = ArrayList()

    val viewModel: HomeViewModel by viewModels {
        (activity as MainActivity).homeFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        dialog = CustomDialog(mContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (binding == null) {
            binding = DataBindingUtil.inflate<FragmentHomeBinding>(
                inflater,
                R.layout.fragment_home,
                container,
                false
            ).apply {
                mViewModel = viewModel
                lifecycleOwner = this@HomeFragment
            }
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar()
        initUI()
        setupObservers()
        dummyData()
        //apiCall()
    }

    //MARK: InitUI
    private fun initUI() {

        //MARK: Pull down to refresh data
        binding?.swipeContainer?.setOnRefreshListener { apiCall() }
        binding?.swipeContainer?.setColorSchemeResources(R.color.colorPrimary)
    }

    //MARK: Api call
    private fun apiCall() {
        viewModel.getFeedList()
    }

    //MARK: Setup observer
    private fun setupObservers() {
        val progress = Observer<Boolean> {
            if (it) {
                dialog?.show()
            } else {
                dialog?.dismiss()
            }
        }

        val validation = Observer<String?> {
            if (it != null) {
                binding?.llParent?.let { it1 -> snackBarError(it1, it) }
            }
        }

        val failApi = Observer<String?> {
            if (it != null) {
                binding?.llParent?.let { it1 -> snackBarError(it1, it) }
            }
        }

        val successResponse = Observer<VideoModel> {
            arrayList = it.data!!
            setData()
        }
        viewModel.validationMessage.observe(viewLifecycleOwner, validation)
        viewModel.progress.observe(viewLifecycleOwner, progress)
        viewModel.apiFail.observe(viewLifecycleOwner, failApi)
        viewModel.successResponse.observe(viewLifecycleOwner, successResponse)
    }

    private fun setData() {
        binding?.swipeContainer?.isRefreshing = false
        if (arrayList.isNotEmpty()) {
            binding?.viewPager?.visible()
            binding?.imgNoDataFound?.gone()
            homeAdapter = HomePagerAdapter(this, arrayList)
            binding?.viewPager?.adapter = homeAdapter

        } else {
            binding?.viewPager?.gone()
            binding?.imgNoDataFound?.visible()
        }
    }

    private fun dummyData() {
        arrayList.clear()

        var item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_1
        item.name = "Sanjay Mangaroliya"
        item.comment = "Morning  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_2
        item.name = "Ann Muyale"
        item.comment = "Afternoon  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_3
        item.name = "Kipyegon"
        item.comment = "Evening  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_1
        item.name = "Sanjay Mangaroliya"
        item.comment = "Morning  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_2
        item.name = "Ann Muyale"
        item.comment = "Afternoon  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_3
        item.name = "Kipyegon"
        item.comment = "Evening  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_1
        item.name = "Sanjay Mangaroliya"
        item.comment = "Morning  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_2
        item.name = "Ann Muyale"
        item.comment = "Afternoon  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_3
        item.name = "Kipyegon"
        item.comment = "Evening  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_1
        item.name = "Sanjay Mangaroliya"
        item.comment = "Morning  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_2
        item.name = "Ann Muyale"
        item.comment = "Afternoon  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        item = VideoItem()
        item.profilePic = ""
        item.videoUrl = "android.resource://" + mContext.packageName + "/" + R.raw.video_3
        item.name = "Kipyegon"
        item.comment = "Evening  training...."
        item.isLike = false
        item.isSave = false
        arrayList.add(item)

        setData()
    }

    //MARK: Toolbar
    private fun toolbar() {
        binding?.toolbar?.imgSearch?.visible()
        binding?.toolbar?.txtToolbarTitle?.visible()
        binding?.toolbar?.txtToolbarTitle?.text = mContext.resources.getString(R.string.explore)
    }
}