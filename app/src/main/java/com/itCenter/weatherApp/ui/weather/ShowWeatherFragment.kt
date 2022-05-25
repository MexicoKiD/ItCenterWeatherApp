package com.itCenter.weatherApp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.itCenter.weatherApp.R
import com.itCenter.weatherApp.SERVER_ERROR
import com.itCenter.weatherApp.data.DataHandler
import com.itCenter.weatherApp.data.dto.WeatherDTO
import com.itCenter.weatherApp.databinding.ShowWeatherFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDate

@AndroidEntryPoint
class ShowWeatherFragment : Fragment() {

    private var _binding: ShowWeatherFragmentBinding? = null
    private val showWeatherViewModel: ShowWeatherViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ShowWeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val city = arguments?.let { ShowWeatherFragmentArgs.fromBundle(it).cityArgs }!!
        val date = arguments?.let { ShowWeatherFragmentArgs.fromBundle(it).date }!!

        showWeatherViewModel.getWeather(city, date)

        showWeatherViewModel.dataHandlerState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataHandler.Success<*> -> showWeather(state.data as WeatherDTO)
                is DataHandler.Failure -> Toast.makeText(activity, SERVER_ERROR, Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_showWeatherFragment_to_searchFragment)
        }

    }

    private fun showWeather(response: WeatherDTO) {
        val city: TextView = binding.city
        val temperature: TextView = binding.temperature
        val date: TextView = binding.date

        city.text = response.cityName
        date.text = response.date.toString()
        temperature.text = response.temperatureInCelsius.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}