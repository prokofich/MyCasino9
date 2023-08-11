package com.example.mycasino9.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino9.R
import com.example.mycasino9.constant.MAIN
import com.example.mycasino9.constant.url_image_background
import com.example.mycasino9.viewmodel.TutorialsViewModel
import kotlinx.android.synthetic.main.fragment_tutorials.*

class TutorialsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background,id_tut_img)

        //загрузка текста с сервера
        val tutorialsViewModel = ViewModelProvider(this)[TutorialsViewModel::class.java]
        tutorialsViewModel.getTextTutorialsGame()
        tutorialsViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_rules_tv_rules1.text = TEXT.body()!![0].text
            id_rules_tv_rules2.text = TEXT.body()!![1].text
        }

        //выход в меню
        id_rules_button_menu.setOnClickListener {
            MAIN.navController.navigate(R.id.action_tutorialsFragment2_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_tutorialsFragment2_to_menuFragment)
        }

    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

}