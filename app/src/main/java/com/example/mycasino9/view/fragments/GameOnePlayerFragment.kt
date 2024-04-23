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
import com.example.mycasino9.model.constant.NUMBER_AVATAR
import com.example.mycasino9.model.constant.listUrlImageDice
import com.example.mycasino9.model.constant.url_image_background_game
import com.example.mycasino9.model.constant.url_image_girl1
import com.example.mycasino9.model.constant.url_image_girl2
import com.example.mycasino9.model.constant.url_image_man1
import com.example.mycasino9.model.constant.url_image_man2
import kotlinx.android.synthetic.main.fragment_game_one_player.*

class GameOnePlayerFragment : Fragment() {

    private var startDiaposon = 0
    private var endDiaposon = 0
    private var listRandomDiaposon = listOf(8,9,10,11,12,13,14,15)
    private var myListPoints = mutableListOf<Int>()
    private var listPoints = listOf(1,2,3,4,5,6)
    private var flagFinish = false
    private var countDice = 0
    private var diaposon = startDiaposon..endDiaposon

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_one_player, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startDiaposon = listRandomDiaposon.shuffled()[0]
        endDiaposon = startDiaposon+3
        diaposon = startDiaposon..endDiaposon

        id_game_one_tv_cel_points.text = "${startDiaposon}..${endDiaposon}"

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background_game,id_game_one_img)

        //загрузка аватарки
        loadSmile(requireArguments().getInt(NUMBER_AVATAR))

        //подбросить кость
        id_game_one_button_throw_dice.setOnClickListener {

            if(!flagFinish){
                countDice+=1
                val randomPoint = listPoints.shuffled()[0]
                myListPoints.add(randomPoint)
                loadImageDice(randomPoint)
                id_game_one_tv_all_points.text = "total points:${myListPoints.sum()}"

                checkWin()

                if(countDice == 3){
                    flagFinish = true
                    id_game_one_button_throw_dice.text = "RESTART"
                }
            }else{
                id_game_one_tv_dice1_points.text = "0"
                id_game_one_tv_dice2_points.text = "0"
                id_game_one_tv_dice3_points.text = "0"
                id_game_one_tv_all_points.text = "total points:0"
                id_game_one_iv_dice1.setImageDrawable(null)
                id_game_one_iv_dice2.setImageDrawable(null)
                id_game_one_iv_dice3.setImageDrawable(null)
                flagFinish = false
                countDice = 0
                id_game_one_button_throw_dice.text = "toss a dice"
                myListPoints = mutableListOf()
                startDiaposon = listRandomDiaposon.shuffled()[0]
                endDiaposon = startDiaposon+3
                diaposon = startDiaposon..endDiaposon
                id_game_one_tv_cel_points.text = "${startDiaposon}..${endDiaposon}"
            }

        }

        //нажать на паузу
        id_game_one_button_pause.setOnClickListener {
            id_game_one_cs_game.isVisible = false
            id_game_one_tv_cel_points.isVisible = false
            id_game_one_cs_pause.isVisible = true
        }

        //продолжить после паузы
        id_game_one_button_continue.setOnClickListener {
            id_game_one_cs_game.isVisible = true
            id_game_one_tv_cel_points.isVisible = true
            id_game_one_cs_pause.isVisible = false
        }

        //закончить на паузе
        id_game_one_button_finish.setOnClickListener {
            MAIN.navController?.navigate(R.id.action_gameOnePlayerFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            id_game_one_cs_game.isVisible = false
            id_game_one_tv_cel_points.isVisible = false
            id_game_one_cs_pause.isVisible = true
        }

    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url : String , id : ImageView) {
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    private fun loadSmile(numberAvatar : Int) {
        when(numberAvatar){
            1 -> { loadBackgroundImage(url_image_girl1,id_game_one_iv_smile) }
            2 -> { loadBackgroundImage(url_image_girl2,id_game_one_iv_smile) }
            3 -> { loadBackgroundImage(url_image_man1,id_game_one_iv_smile)  }
            4 -> { loadBackgroundImage(url_image_man2,id_game_one_iv_smile)  }
        }
    }

    private fun loadImageDice(points : Int) {
        when(countDice){
            1 -> {
                loadBackgroundImage(listUrlImageDice[points-1],id_game_one_iv_dice1)
                id_game_one_tv_dice1_points.text = points.toString()
            }
            2 -> {
                loadBackgroundImage(listUrlImageDice[points-1],id_game_one_iv_dice2)
                id_game_one_tv_dice2_points.text = points.toString()
            }
            3 -> {
                loadBackgroundImage(listUrlImageDice[points-1],id_game_one_iv_dice3)
                id_game_one_tv_dice3_points.text = points.toString()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkWin(){
        if(myListPoints.sum() in diaposon){
            //победа
            id_game_one_tv_all_points.text = "WIN!"
            showToast("you have won!")
            flagFinish = true
            id_game_one_button_throw_dice.text = "RESTART"
            MAIN.updateCountWinOnePlayer()
        }else{
            if(countDice == 3){
                showToast("you've lost")
            }
        }
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String) = Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()

}