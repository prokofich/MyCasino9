package com.example.mycasino9.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import com.example.mycasino9.R
import com.example.mycasino9.model.constant.MAIN
import com.example.mycasino9.model.constant.url_image_background
import com.example.mycasino9.model.constant.url_image_emblema_dice
import com.example.mycasino9.model.constant.url_image_one_player
import com.example.mycasino9.model.constant.url_image_two_players
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background,id_menu_img)

        //загрузка эмблемы игры
        loadBackgroundImage(url_image_emblema_dice,id_menu_iv_img_dice)

        //загрузка однопользовательской игры
        loadBackgroundImage(url_image_one_player,id_menu_iv_img_one_player)

        //загрузка многопользовательской игры
        loadBackgroundImage(url_image_two_players,id_menu_iv_img_two_players)

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.finishAffinity()
        }

        //переход к одиночной игре
        id_menu_button_game_one_player.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_menuFragment_to_settingsOnePlayerFragment)
        }

        id_menu_button_game_two_players.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_menuFragment_to_settingsTwoPlayersFragment)
        }

        //кнопка выхода
        id_menu_button_quit.setOnClickListener {
            MAIN.finishAffinity()
        }

        //кнопка туториалов
        id_menu_button_tutorials.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_menuFragment_to_tutorialsFragment2)
        }

    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)

    }

}