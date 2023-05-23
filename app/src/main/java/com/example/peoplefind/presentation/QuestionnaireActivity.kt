package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityQuestionnaireBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.domain.model.Interest
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.presentation.vm.InterestViewModel
import com.example.peoplefind.presentation.vm.QuestionnaireViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionnaireActivity : AppCompatActivity() {
    private val questionnaireViewModel by viewModel<QuestionnaireViewModel>()
    private val interestViewModel by viewModels<InterestViewModel>()
    private lateinit var binding: ActivityQuestionnaireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        val interests = resources.getStringArray(R.array.interests)
        interests.forEach {
            addChip(interests.indexOf(it), it)
            questionnaireViewModel.addInterestDescription(interests.indexOf(it), "")
        }

        val interestsDescription = questionnaireViewModel.interestsDescriptionList.value

        interestViewModel.interest.observe(this@QuestionnaireActivity) {
            questionnaireViewModel.addInterestDescription(it.first, it.second)
        }

        buttonNext.setOnClickListener {
            questionnaireViewModel.fillQuestionnaire(
                name = editTextName.text.toString(),
                surname = "",
                birthDate = editTextAge.text.toString(),
                address = QuestionnaireAddress(
                    region = "Россия",
                    city = editTextCity.text.toString(),
                    street = "",
                    numberOfHome = ""
                ),
                interests = listOf(
                    Interest(name = interests[0], description = interestsDescription?.get(0) ?: ""),
                    Interest(name = interests[1], description = interestsDescription?.get(1) ?: ""),
                    Interest(name = interests[2], description = interestsDescription?.get(2) ?: "")
                )
            )
        }

        questionnaireViewModel.fillQuestionnaireResult.observe(this@QuestionnaireActivity) { result ->
            result.onLoading {
                skeletonNext.showSkeleton()
            }.onSuccess {
                skeletonNext.showOriginal()
                val intent = Intent(this@QuestionnaireActivity, MainActivity::class.java)
                startActivity(intent)
            }.onFailure { message, error ->
                skeletonNext.showOriginal()
            }
        }
    }

    private fun addChip(chipId: Int, chipText: String) {
        Chip(this).apply {
            id = chipId
            text = chipText
            isClickable = true
            isCheckable = true
            setOnClickListener {
                if (!this.isChecked) {
                    questionnaireViewModel.removeInterestDescription(chipId)
                    return@setOnClickListener
                }

                InterestBottomSheet.newInstance(chipId, chipText)
                    .show(supportFragmentManager, InterestBottomSheet.TAG)

                //val dialog = createInterestDialog(chipText)
                //val interestDialog = dialog.first.show()
                //val interestDialogBinding = dialog.second
                /*interestDialogBinding.buttonConfirmDescription.setOnClickListener {
                    questionnaireViewModel.addInterestDescription(
                        chipId, interestDialogBinding.editTextInterestDescription.text.toString()
                    )
                    interestDialog.dismiss()
                    Timber.d("Interest description: ${interestDialogBinding.editTextInterestDescription.text}")
                }*/
            }
            binding.chipGroupInterests.addView(this)
        }
    }
}