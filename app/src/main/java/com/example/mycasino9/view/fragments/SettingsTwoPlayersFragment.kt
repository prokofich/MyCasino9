package com.example.mycasino9.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mycasino9.R
import com.example.mycasino9.model.constant.MAIN
import com.example.mycasino9.model.constant.NAME_PLAYER_1
import com.example.mycasino9.model.constant.NAME_PLAYER_2
import com.example.mycasino9.model.constant.NUMBER_AVATAR_PLAYER_1
import com.example.mycasino9.model.constant.NUMBER_AVATAR_PLAYER_2
import com.example.mycasino9.model.constant.url_image_background
import com.example.mycasino9.model.constant.url_image_girl1
import com.example.mycasino9.model.constant.url_image_girl2
import com.example.mycasino9.model.constant.url_image_man1
import com.example.mycasino9.model.constant.url_image_man2
import kotlinx.android.synthetic.main.fragment_settings_two_players.*

class SettingsTwoPlayersFragment : Fragment() {

    private var numberAvatarPlayer1 = 0
    private var numberAvatarPlayer2 = 0
    private var flagReadyPlayer1 = false
    private var flagReadyPlayer2 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_two_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background,id_set_two_img)

        //загрузка аватарок для двух игроков
        loadBackgroundImage(url_image_girl1,id_set_two_img_smile1)
        loadBackgroundImage(url_image_girl2,id_set_two_img_smile2)
        loadBackgroundImage(url_image_man1,id_set_two_img_smile3)
        loadBackgroundImage(url_image_man2,id_set_two_img_smile4)
        loadBackgroundImage(url_image_girl1,id_set_two_img_smile1_player2)
        loadBackgroundImage(url_image_girl2,id_set_two_img_smile2_player2)
        loadBackgroundImage(url_image_man1,id_set_two_img_smile3_player2)
        loadBackgroundImage(url_image_man2,id_set_two_img_smile4_player2)

        //выбор 1 аватарки первого игрока
        id_set_two_radio1.setOnClickListener {
            numberAvatarPlayer1 = 1
            id_set_two_radio2.isChecked = false
            id_set_two_radio3.isChecked = false
            id_set_two_radio4.isChecked = false
        }

        //выбор 2 аватарки первого игрока
        id_set_two_radio2.setOnClickListener {
            numberAvatarPlayer1 = 2
            id_set_two_radio1.isChecked = false
            id_set_two_radio3.isChecked = false
            id_set_two_radio4.isChecked = false
        }

        //выбор 3 аватарки первого игрока
        id_set_two_radio3.setOnClickListener {
            numberAvatarPlayer1= 3
            id_set_two_radio2.isChecked = false
            id_set_two_radio1.isChecked = false
            id_set_two_radio4.isChecked = false
        }

        //выбор 4 аватарки первого игрока
        id_set_two_radio4.setOnClickListener {
            numberAvatarPlayer1 = 4
            id_set_two_radio2.isChecked = false
            id_set_two_radio3.isChecked = false
            id_set_two_radio1.isChecked = false
        }

        //готовность певрого игрока + переход к игре
        id_set_two_button_ready1.setOnClickListener {
            if(id_set_two_et_1.text.isNotEmpty()){
               if(numberAvatarPlayer1!=0){
                   id_set_two_checkbox.isChecked = true
                   flagReadyPlayer1 = true
                   if(flagReadyPlayer2){

                       val bundle = Bundle()
                       bundle.putInt(NUMBER_AVATAR_PLAYER_1,numberAvatarPlayer1)
                       bundle.putInt(NUMBER_AVATAR_PLAYER_2,numberAvatarPlayer2)
                       bundle.putString(NAME_PLAYER_1,id_set_two_et_1.text.toString())
                       bundle.putString(NAME_PLAYER_2,id_set_two_et_2.text.toString())

                       MAIN.navController?.navigate(R.id.action_settingsTwoPlayersFragment_to_gameTwoPlayersFragment,bundle)

                   }
               }else{
                   //нет аватарки
                   showToast("choose avatars")
               }
            }else{
                //нет имени
                showToast("you need to enter the names")
            }
        }

        //готовность второго игрока + переход к игре
        id_set_two_button_ready2.setOnClickListener {

            if(id_set_two_et_2.text.isNotEmpty()){
                if(numberAvatarPlayer2!=0){
                    id_set_two_checkbox2.isChecked = true
                    flagReadyPlayer2 = true
                    if(flagReadyPlayer1){

                        val bundle = Bundle()
                        bundle.putInt(NUMBER_AVATAR_PLAYER_1,numberAvatarPlayer1)
                        bundle.putInt(NUMBER_AVATAR_PLAYER_2,numberAvatarPlayer2)
                        bundle.putString(NAME_PLAYER_1,id_set_two_et_1.text.toString())
                        bundle.putString(NAME_PLAYER_2,id_set_two_et_2.text.toString())

                        MAIN.navController?.navigate(R.id.action_settingsTwoPlayersFragment_to_gameTwoPlayersFragment,bundle)

                    }
                }else{
                    //нет аватарки
                    showToast("choose avatars")
                }
            }else{
                //нет имени
                showToast("you need to enter the names")
            }
        }

        //выбор 1 аватарки второго игрока
        id_set_two_radio1_player2.setOnClickListener {
            numberAvatarPlayer2 = 1
            id_set_two_radio2_player2.isChecked = false
            id_set_two_radio3_player2.isChecked = false
            id_set_two_radio4_player2.isChecked = false
        }

        //выбор 2 аватарки второго игрока
        id_set_two_radio2_player2.setOnClickListener {
            numberAvatarPlayer2 = 2
            id_set_two_radio1_player2.isChecked = false
            id_set_two_radio3_player2.isChecked = false
            id_set_two_radio4_player2.isChecked = false
        }

        //выбор 3 аватарки второго игрока
        id_set_two_radio3_player2.setOnClickListener {
            numberAvatarPlayer2 = 3
            id_set_two_radio2_player2.isChecked = false
            id_set_two_radio1_player2.isChecked = false
            id_set_two_radio4_player2.isChecked = false
        }

        //выбор 4 аватарки второго игрока
        id_set_two_radio4_player2.setOnClickListener {
            numberAvatarPlayer2 = 4
            id_set_two_radio2_player2.isChecked = false
            id_set_two_radio3_player2.isChecked = false
            id_set_two_radio1_player2.isChecked = false
        }

    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String) = Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()

}