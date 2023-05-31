package com.example.peoplefind.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.peoplefind.databinding.FragmentHomeBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.domain.model.response.QuestionnaireList
import com.example.peoplefind.presentation.AboutUserCardActivity
import com.example.peoplefind.presentation.adapter.QuestionnaireAdapter
import com.example.peoplefind.presentation.util.showErrorDialog
import com.example.peoplefind.presentation.vm.HomeViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment(), CardStackListener, QuestionnaireAdapter.OnClickListener {
    private val homeViewModel by viewModel<HomeViewModel>()
    private val adapter by lazy { QuestionnaireAdapter(this) }
    private lateinit var binding: FragmentHomeBinding

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getRecommendations()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        val manager = CardStackLayoutManager(requireActivity(), this@HomeFragment)

        manager.apply {
            setStackFrom(StackFrom.None)
            setVisibleCount(3)
            setTranslationInterval(8.0f)
            setScaleInterval(0.95f)
            setSwipeThreshold(0.3f)
            setMaxDegree(20.0f)
            setDirections(Direction.VERTICAL)
            setCanScrollHorizontal(false)
            setCanScrollVertical(true)
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        if (cardQuestionnaires.layoutManager == null) {
            cardQuestionnaires.layoutManager = manager
            cardQuestionnaires.adapter = adapter
            cardQuestionnaires.itemAnimator.apply {
                if (this is DefaultItemAnimator) {
                    supportsChangeAnimations = false
                }
            }
        }

        homeViewModel.recommendations.observe(viewLifecycleOwner) { result ->
            result.onLoading {
                Timber.d("Loading Recommendations...")
                buttonLike.isEnabled = false
                buttonSkip.isEnabled = false
                skeletonQuestionnaires.showSkeleton()
            }.onSuccess {
                adapter.submitList(it)
                skeletonQuestionnaires.showOriginal()
                buttonLike.isEnabled = true
                buttonSkip.isEnabled = true
                Timber.d("Recommendations loaded")
            }.onFailure { message, error ->
                buttonLike.isEnabled = false
                buttonSkip.isEnabled = false
                skeletonQuestionnaires.showOriginal()
                requireActivity().showErrorDialog(
                    title = "Не удалось получить список анкет",
                    message = message
                )
            }
        }

        buttonSkip.setOnClickListener {
            if (adapter.currentList.isEmpty()) return@setOnClickListener
            homeViewModel.dislikeQuestionnaire(adapter.currentList[manager.topPosition].id)
        }

        buttonLike.setOnClickListener {
            if (adapter.currentList.isEmpty()) return@setOnClickListener
            homeViewModel.likeQuestionnaire(adapter.currentList[manager.topPosition].id)
        }

        homeViewModel.questionnaireRateDislikeResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Top)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                manager.setSwipeAnimationSetting(setting)
                cardQuestionnaires.swipe()
                adapter.currentList.toMutableList()
            }.onFailure { message, error ->

            }
        }

        homeViewModel.questionnaireRateLikeResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                manager.setSwipeAnimationSetting(setting)
                cardQuestionnaires.swipe()
            }.onFailure { message, error ->

            }
        }
    }

    private fun removeRatedCardFromList(currentList: List<QuestionnaireList>) {
        val list = ArrayList<QuestionnaireList>().apply {
            addAll(currentList)
            removeLast()
        }
        adapter.submitList(list)
    }

    override fun onCardSwiped(direction: Direction?) {
        removeRatedCardFromList(adapter.currentList)
        if ((binding.cardQuestionnaires.layoutManager as CardStackLayoutManager).topPosition == adapter.itemCount - 1) {
            // Data pagination
        }
    }

    override fun onCardClick(item: QuestionnaireList) {
        val intent = Intent(requireActivity(), AboutUserCardActivity::class.java).apply {
            putExtra("UserName", item.name)
            putExtra("UserCity", item.address.city)
        }
        startActivity(intent)
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}
}