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
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.domain.model.response.Questionnaire
import com.example.peoplefind.presentation.AboutUserCardActivity
import com.example.peoplefind.presentation.adapter.QuestionnaireAdapter
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod

class HomeFragment : Fragment(), CardStackListener, QuestionnaireAdapter.OnClickListener {
    private val adapter by lazy { QuestionnaireAdapter(this) }
    private lateinit var binding: FragmentHomeBinding

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
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

        buttonSkip.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardQuestionnaires.swipe()
        }

        buttonLike.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardQuestionnaires.swipe()
        }

        if (adapter.currentList.size == 0) {
            adapter.submitList(listOf(
                Questionnaire("Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList()),
                Questionnaire("Не Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList()),
                Questionnaire("Точно не Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList()),
                Questionnaire("Совсем не Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList())
            ))
            veilQuestionnaire.unVeil()
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        if ((binding.cardQuestionnaires.layoutManager as CardStackLayoutManager).topPosition == adapter.itemCount - 1) {
            binding.veilQuestionnaire.veil()
            val oldList = adapter.currentList
            adapter.submitList(oldList.plus(listOf(
                Questionnaire("Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList()),
                Questionnaire("Не Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList()),
                Questionnaire("Точно не Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList()),
                Questionnaire("Совсем не Владимир", "", "01-01-04", QuestionnaireAddress("Россия", "Владимировосток", "", ""), emptyList())
            )))
            binding.veilQuestionnaire.unVeil()
        }
    }

    override fun onCardClick(item: Questionnaire) {
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