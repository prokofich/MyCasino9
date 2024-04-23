package com.example.mycasino9.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.mycasino9.R
import com.example.mycasino9.model.constant.MAIN
import com.example.mycasino9.model.constant.NAME_PLAYER_1
import com.example.mycasino9.model.constant.NAME_PLAYER_2
import com.example.mycasino9.model.constant.NUMBER_AVATAR_PLAYER_1
import com.example.mycasino9.model.constant.NUMBER_AVATAR_PLAYER_2
import com.example.mycasino9.model.constant.listUrlImageDice
import com.example.mycasino9.model.constant.url_image_background_game
import com.example.mycasino9.model.constant.url_image_girl1
import com.example.mycasino9.model.constant.url_image_girl2
import com.example.mycasino9.model.constant.url_image_man1
import com.example.mycasino9.model.constant.url_image_man2
import kotlinx.android.synthetic.main.fragment_game_two_players.*

class GameTwoPlayersFragment : Fragment() {

    private var countDicePlayer1 = 0
    private var countDicePlayer2 = 0
    private var listPointsPlayer1 = mutableListOf<Int>()
    private var listPointsPlayer2 = mutableListOf<Int>()
    private var listPoints = listOf<Int>(1,2,3,4,5,6)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_two_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showToast("whoever gets closer to 15 points will win")

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background_game,id_game_two_img)

        //загрузка имен
        id_game_two_tv_name_player1.text = requireArguments().getString(NAME_PLAYER_1)
        id_game_two_tv_name_player2.text = requireArguments().getString(NAME_PLAYER_2)

        //загрузка аватарок
        loadSmiles(requireArguments().getInt(NUMBER_AVATAR_PLAYER_1),requireArguments().getInt(
            NUMBER_AVATAR_PLAYER_2
        ))

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            id_game_two_cs_game.isVisible = false
            id_game_two_button_done.isVisible = false
            id_game_two_cs_pause.isVisible = true
        }

        //финиш после паузы
        id_game_two_button_finish.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_gameTwoPlayersFragment_to_menuFragment)
        }

        //старт после паузы
        id_game_two_button_continue.setOnClickListener {
            id_game_two_cs_game.isVisible = true
            id_game_two_button_done.isVisible = true
            id_game_two_cs_pause.isVisible = false
        }

        //бросок 1 игрока
        id_game_two_button_throw_dice1.setOnClickListener {
            if(countDicePlayer1!=3){
                id_game_two_button_done.isVisible = true
                countDicePlayer1+=1

                val randomPoint = listPoints.shuffled()[0]
                listPointsPlayer1.add(randomPoint)
                loadImageDicePlayer1(randomPoint)
                id_game_two_tv_all_points_player1.text = listPointsPlayer1.sum().toString()

                checkLossPlayer1()
            }
        }

        //бросок 2 игрока
        id_game_two_button_throw_dice2.setOnClickListener {
            if(countDicePlayer2!=3){
                id_game_two_button_done.isVisible = true
                countDicePlayer2+=1

                val randomPoint = listPoints.shuffled()[0]
                listPointsPlayer2.add(randomPoint)
                loadImageDicePlayer2(randomPoint)
                id_game_two_tv_all_points_player2.text = listPointsPlayer2.sum().toString()

                checkLossPlayer2()
            }
        }

        //рестарт
        id_game_two_button_restart.setOnClickListener {
            idgame_two_iv_img_dice1_player1.setImageDrawable(null)
            idgame_two_iv_img_dice2_player1.setImageDrawable(null)
            idgame_two_iv_img_dice3_player1.setImageDrawable(null)
            idgame_two_iv_img_dice1_player2.setImageDrawable(null)
            idgame_two_iv_img_dice2_player2.setImageDrawable(null)
            idgame_two_iv_img_dice3_player2.setImageDrawable(null)
            id_game_two_tv_all_points_player1.text = "0"
            id_game_two_tv_all_points_player2.text = "0"
            id_game_two_tv_name_player1.text = requireArguments().getString(NAME_PLAYER_1)
            id_game_two_tv_name_player2.text = requireArguments().getString(NAME_PLAYER_2)
            listPointsPlayer1 = mutableListOf()
            listPointsPlayer2 = mutableListOf()
            countDicePlayer1 = 0
            countDicePlayer2 = 0
            id_game_two_button_restart.isVisible = false
        }

        //посмотреть результаты
        id_game_two_button_done_galka.setOnClickListener{
            if(listPointsPlayer1.sum()>listPointsPlayer2.sum()){
                //победил1
                winPlayer1()
            }else{
                if(listPointsPlayer1.sum()<listPointsPlayer2.sum()){
                    //победил2
                    winPlayer2()
                }else{
                    //ничья
                    winPlayer1Player2()
                }
            }
        }

    }

    //функция загрузки аватарок
    private fun loadSmiles(number1 : Int , number2 : Int) {
        when(number1){
            1 -> { loadBackgroundImage(url_image_girl1,id_game_two_iv_player1) }
            2 -> { loadBackgroundImage(url_image_girl2,id_game_two_iv_player1) }
            3 -> { loadBackgroundImage(url_image_man1,id_game_two_iv_player1)  }
            4 -> { loadBackgroundImage(url_image_man2,id_game_two_iv_player1)  }
        }
        when(number2){
            1 -> { loadBackgroundImage(url_image_girl1,id_game_two_iv_player2) }
            2 -> { loadBackgroundImage(url_image_girl2,id_game_two_iv_player2) }
            3 -> { loadBackgroundImage(url_image_man1,id_game_two_iv_player2)  }
            4 -> { loadBackgroundImage(url_image_man2,id_game_two_iv_player2)  }
        }
    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String) = Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()

    private fun loadImageDicePlayer1(points:Int){
        when(countDicePlayer1){
            1 -> { loadBackgroundImage(listUrlImageDice[points-1],idgame_two_iv_img_dice1_player1) }
            2 -> { loadBackgroundImage(listUrlImageDice[points-1],idgame_two_iv_img_dice2_player1) }
            3 -> { loadBackgroundImage(listUrlImageDice[points-1],idgame_two_iv_img_dice3_player1) }
        }
    }

    private fun loadImageDicePlayer2(points:Int){
        when(countDicePlayer2){
            1 -> { loadBackgroundImage(listUrlImageDice[points-1],idgame_two_iv_img_dice1_player2) }
            2 -> { loadBackgroundImage(listUrlImageDice[points-1],idgame_two_iv_img_dice2_player2) }
            3 -> { loadBackgroundImage(listUrlImageDice[points-1],idgame_two_iv_img_dice3_player2) }
        }
    }

    private fun checkLossPlayer1(){
        if(listPointsPlayer1.sum() > 15){
            winPlayer2()
        }
    }

    private fun checkLossPlayer2(){
        if(listPointsPlayer2.sum() > 15){
            winPlayer1()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun winPlayer1(){
        id_game_two_button_done.isVisible = false
        id_game_two_button_restart.isVisible = true
        id_game_two_tv_name_player2.text = "the loser"
        id_game_two_tv_name_player1.text = "winner"
        showToast("player ${requireArguments().getString(NAME_PLAYER_1)} won!")
    }

    @SuppressLint("SetTextI18n")
    private fun winPlayer2(){
        id_game_two_button_done.isVisible = false
        id_game_two_button_restart.isVisible = true
        id_game_two_tv_name_player1.text = "the loser"
        id_game_two_tv_name_player2.text = "winner"
        showToast("player ${requireArguments().getString(NAME_PLAYER_2)} won!")
    }

    @SuppressLint("SetTextI18n")
    private fun winPlayer1Player2(){
        id_game_two_button_done.isVisible = false
        id_game_two_button_restart.isVisible = true
        id_game_two_tv_name_player1.text = "winner"
        id_game_two_tv_name_player2.text = "winner"
        showToast("combat draw!")
    }

}