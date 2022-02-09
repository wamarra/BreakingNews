package br.com.breakingnews.presentation.main

import androidx.lifecycle.*
import br.com.breakingnews.core.utils.ViewState
import br.com.breakingnews.data.BreakingNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BreakingNewsRepository
) : ViewModel() {

    val breakingNewsData = repository.getBreakingNewsFromDb()
        .onStart {
            repository.getBreakingNews()
            ViewState.Loading
        }
        .catch {
            ViewState.Error("Error")
        }
        .asLiveData()
}