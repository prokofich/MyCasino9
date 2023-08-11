package com.example.mycasino9.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino9.R
import com.example.mycasino9.constant.*
import com.example.mycasino9.viewmodel.TutorialsOnePlayerViewModel
import kotlinx.android.synthetic.main.fragment_settings_one_player.*

class SettingsOnePlayerFragment : Fragment() {

    private var numberAvatar = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_one_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background,id_set_one_img)

        //загрузка предполагаемых аватарок
        loadBackgroundImage(url_image_girl1,id_set_one_img_smile1)
        loadBackgroundImage(url_image_girl2,id_set_one_img_smile2)
        loadBackgroundImage(url_image_man1,id_set_one_img_smile3)
        loadBackgroundImage(url_image_man2,id_set_one_img_smile4)

        val onePlayerViewModel = ViewModelProvider(this)[TutorialsOnePlayerViewModel::class.java]
        onePlayerViewModel.getTextOnePlayer()
        onePlayerViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_set_one_tv_rules.text = TEXT.body()!!.text
        }

        //загрузка количества побед
        id_set_one_tv_record.text = "total number of wins:${MAIN.getCountWinOnePlayer()}"

        //переход обратно в меню
        id_set_one_button_menu.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsOnePlayerFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_settingsOnePlayerFragment_to_menuFragment)
        }

        //выбор 1 аватарки
        id_set_one_radio1.setOnClickListener {
            numberAvatar = 1
            id_set_one_radio2.isChecked = false
            id_set_one_radio3.isChecked = false
            id_set_one_radio4.isChecked = false
        }

        //выбор 2 аватарки
        id_set_one_radio2.setOnClickListener {
            numberAvatar = 2
            id_set_one_radio1.isChecked = false
            id_set_one_radio3.isChecked = false
            id_set_one_radio4.isChecked = false
        }

        //выбор 3 аватарки
        id_set_one_radio3.setOnClickListener {
            numberAvatar = 3
            id_set_one_radio2.isChecked = false
            id_set_one_radio1.isChecked = false
            id_set_one_radio4.isChecked = false
        }

        //выбор 4 аватарки
        id_set_one_radio4.setOnClickListener {
            numberAvatar = 4
            id_set_one_radio2.isChecked = false
            id_set_one_radio3.isChecked = false
            id_set_one_radio1.isChecked = false
        }

        //переход к самой игре
        id_set_one_button_next.setOnClickListener {
            if(numberAvatar==0){
                showToast("choose an avatar")
            }else{
                var bundle = Bundle()
                bundle.putInt(NUMBER_AVATAR,numberAvatar)
                MAIN.navController.navigate(R.id.action_settingsOnePlayerFragment_to_gameOnePlayerFragment,bundle)
            }
        }




    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

}