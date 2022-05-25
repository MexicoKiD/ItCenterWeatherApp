package com.itCenter.weatherApp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.itCenter.weatherApp.*
import com.itCenter.weatherApp.data.DataHandler
import com.itCenter.weatherApp.data.dto.WeatherDTO
import com.itCenter.weatherApp.databinding.SearchWeatherFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDate

@AndroidEntryPoint
class SearchWeatherFragment : Fragment() {

    private var _binding: SearchWeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: SearchWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchWeatherFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val input = binding.textInput.text
                .toString()
                .trimAndUppercase()
            when (input.cityOrPostalCodeInputIsValid()) {
                true -> {
                    val date = LocalDate.now().toString()
                    homeViewModel.getWeatherForCityAndDate(input, date)
                }
                false -> binding.textInputLayout.error = INCORRECT_DATA
            }


            homeViewModel.dataHandlerState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is DataHandler.Success<*> -> {
                        val weather = (state.data as WeatherDTO)
                        val action =
                            SearchWeatherFragmentDirections.actionSearchFragmentToShowWeatherFragment(
                                weather.cityName.trimAndUppercase(),
                                weather.date.toString()
                            )
                        setFragmentResult(
                            REQUEST_KEY,
                            bundleOf(
                                (CITY to weather.cityName.trimAndUppercase()),
                                (DATE to weather.date.toString())
                            )
                        )
                        if(findNavController(view).currentDestination?.id==R.id.searchFragment) {
                            findNavController(view).navigate(action)
                        }
                    }
                    is DataHandler.Failure -> Toast.makeText(
                        activity,
                        SERVER_ERROR,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}