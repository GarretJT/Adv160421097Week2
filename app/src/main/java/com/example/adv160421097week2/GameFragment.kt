package com.example.adv160421097week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.adv160421097week2.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    private var num1: Int = 0
    private var num2: Int = 0
    public var result = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater,container, false)
        return binding.root
    }

    private fun generateRandomNumbers() {
        num1 = Random.nextInt(101) // Random number between 0 and 100
        num2 = Random.nextInt(101) // Random number between 0 and 100
    }

    private fun displayQuestion() {
        binding.txtMath.text = "${num1} + ${num2}"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$playerName's Turn"
        }

        generateRandomNumbers();
        displayQuestion();

        binding.btnSubmit.setOnClickListener {
            val answer = binding.txtAnswer.text.toString()
            if (answer == (num1 + num2).toString()) {
                result += 1;
                generateRandomNumbers();
                displayQuestion();
            } else {
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(result)
                Navigation.findNavController(it).navigate(action)
            }
        }
        binding.buttonTestFragment.setOnClickListener {
                val action = GameFragmentDirections.actionTestFragment()
                Navigation.findNavController(it).navigate(action)
        }
    }
}