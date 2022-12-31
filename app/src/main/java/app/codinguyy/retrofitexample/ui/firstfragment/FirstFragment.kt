package app.codinguyy.retrofitexample.ui.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import app.codinguyy.retrofitexample.databinding.FragmentFirstBinding
import app.codinguyy.retrofitexample.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val viewModel by viewModel<FirstFragmentViewModel>()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtonListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButtonListener() {
        binding.buttonFirst.setOnClickListener {
            viewModel.getRandomJokeCoroutineLiveData().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.NetworkCallSuccess -> {
                        val jokes = it.data
                        binding.textviewFirst.text = jokes.toString()
                        binding.buttonFirst.isClickable = true
                        binding.progressbar.isVisible = false
                    }
                    is Resource.NetworkCallError -> {
                        binding.textviewFirst.text = it.exception.toString()
                        binding.buttonFirst.isClickable = true
                        binding.progressbar.isVisible = false
                    }
                    is Resource.NetworkCallLoading -> {
                        binding.textviewFirst.text = "loading"
                        binding.buttonFirst.isClickable = false
                        binding.progressbar.isVisible = true
                    }
                    else -> {
                    }
                }
            }
        }

        binding.buttonSecond.setOnClickListener {
            viewModel.getRandomRokeLiveData()
            viewModel.jokesLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.NetworkCallSuccess -> {
                        val jokes = it.data
                        binding.textviewFirst.text = jokes.toString()
                        binding.buttonFirst.isClickable = true
                        binding.progressbar.isVisible = false
                    }
                    is Resource.NetworkCallError -> {
                        binding.textviewFirst.text = it.exception.toString()
                        binding.buttonFirst.isClickable = true
                        binding.progressbar.isVisible = false
                    }
                    is Resource.NetworkCallLoading -> {
                        binding.textviewFirst.text = "loading"
                        binding.buttonFirst.isClickable = false
                        binding.progressbar.isVisible = true
                    }
                    else -> {
                    }
                }
            }
        }

        binding.buttonFour.setOnClickListener {
            viewModel
                .getRandomJokeViaRxKotlinObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    binding.textviewFirst.text = it.toString()
                    binding.buttonFirst.isClickable = true
                    binding.progressbar.isVisible = false
                }, {
                    binding.textviewFirst.text = it.toString()
                    binding.buttonFirst.isClickable = true
                    binding.progressbar.isVisible = false
                }, {
                })
        }
    }
}
