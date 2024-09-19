package com.br.ifinancas.ui.screens.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.ifinancas.db.models.FiModel.FiModel
import com.br.ifinancas.db.models.FiModel.FiRepository
import com.br.ifinancas.db.models.FiModel.FiiWithName
import com.br.ifinancas.db.models.FiiNamesModel.FiisNameRepository
import com.br.ifinancas.db.models.FiiNamesModel.FiisNamesModel
import com.br.ifinancas.db.models.PatrimonioModel.PatrimonioModel
import com.br.ifinancas.db.models.PatrimonioModel.PatrimonioRepository
import com.br.ifinancas.db.models.PatrimonioModel.PatrimonioWithName
import com.br.ifinancas.db.models.PatrimonioNamesModel.PatrimonioNamesModel
import com.br.ifinancas.db.models.PatrimonioNamesModel.PatrimonioNamesRepository
import com.br.ifinancas.services.DateTimeService
import com.br.ifinancas.utils.AppUtils.Companion.AppTag
import com.br.ifinancas.utils.AppUtils.Companion.getMesAno
import com.br.ifinancas.utils.AppUtils.Companion.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val dateTimeService: DateTimeService,
    private val fiisNameRepository: FiisNameRepository,
    private val fiiRepository: FiRepository,
    private val patrimonioRepository: PatrimonioRepository,
    private val patrimonioNamesRepository: PatrimonioNamesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    val allFiisName = fiisNameRepository.allFiisName

    init {
        _uiState.update { currentState ->
            currentState.copy(
                monthSelected = dateTimeService.getMonthName(),
                monthYear = getMesAno()
            )
        }
    }

    fun togglePopupRegisterVisibility() {
        _uiState.update { it.copy(popupAddRegisterIsVisible = !it.popupAddRegisterIsVisible) }
    }

    /*PATRIMONIO*/

    fun deletePatrimonio(id: Int, value: Float) = viewModelScope.launch {

        try {
            patrimonioRepository.delete(id)

            _uiState.update { currentState ->
                currentState.copy(
                    patrimonioList = currentState.patrimonioList.filter { it.patrimonioModel.id != id },
                    patrimonioTotalCount = currentState.patrimonioTotalCount - value
                )
            }
        } catch (_: Exception) {
        }
    }

    fun loadPatrimonios() = viewModelScope.launch {
        val list =
            patrimonioRepository.getAll(_uiState.value.monthYear).firstOrNull() ?: emptyList()

        val patrimonioTotalCount = patrimonioRepository.getPatrimonioTotal(_uiState.value.monthYear)
        _uiState.update { currentState ->
            currentState.copy(
                patrimonioList = list,
                patrimonioTotalCount = patrimonioTotalCount
            )
        }
    }

    fun togglePatrimonioInputVisibility() {
        _uiState.update { it.copy(inputPatrimonioNameIsVisible = !it.inputPatrimonioNameIsVisible) }
    }

    fun onChangePatrimonioName(text: String) {
        _uiState.update { it.copy(patrimonioName = text) }
    }

    fun onChangePatrimonioTotal(text: String) {
        _uiState.update { it.copy(patrimonioTotalInput = text) }
    }

    fun insertPatrimonio(context: Context) = viewModelScope.launch {
        var id: Int = -1;
        try {
            id =
                patrimonioNamesRepository.insert(PatrimonioNamesModel(nome = _uiState.value.patrimonioName))
            showToast(context, "Patrimonio cadastrado")
        } catch (e: Exception) {
            showToast(context, "Erro ao cadastrar patrimonio - 1")
            Log.e(AppTag, e.message.toString())
        }

        try {
            // Loop para criar registros para os próximos 360 meses (30 anos)
            for (i in 0 until 360) {
                val mesAno = getMesAno(i) // Gera o mês e ano com base no incremento
                patrimonioRepository.insert(
                    PatrimonioModel(
                        id_patrimonio_name = id,
                        mes_ano = mesAno
                    )
                )
            }

            _uiState.update { currentState ->
                currentState.copy(
                    inputPatrimonioNameIsVisible = false,
                    reloadScreen = true
                )
            }
        } catch (e: Exception) {
            showToast(context, "Erro ao cadastrar patrimonio - 2")
            Log.e(AppTag, e.message.toString())
        }
    }

    fun selectPatrimonioForEdit(patrimonioWithName: PatrimonioWithName) {
        if (uiState.value.patrimonioSelectedForEdit != null) {
            _uiState.update { currentState ->
                currentState.copy(
                    patrimonioSelectedForEdit = null
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    patrimonioSelectedForEdit = patrimonioWithName,
                    patrimonioTotalInput = patrimonioWithName.patrimonioModel.total.toString()
                )
            }
        }

    }

    fun updatePatrimonio() = viewModelScope.launch {
        val patrimonioWithName = uiState.value.patrimonioSelectedForEdit!!
        val id = patrimonioWithName.patrimonioModel.id
        val patrimonioNamesId = patrimonioWithName.patrimonioModel.id_patrimonio_name
        val total = uiState.value.patrimonioTotalInput
        val mesAno = uiState.value.monthYear

        var v = 0.0f
        if (total.isNotEmpty()) {
            v = total.toFloat() / 100
        }

        val newPatrimonioModel =
            PatrimonioModel(id, patrimonioNamesId, v, mesAno)

        val newPatrimonioWithName =
            PatrimonioWithName(newPatrimonioModel, patrimonioWithName.patrimonioNames)

        try {
            patrimonioRepository.update(newPatrimonioModel)
            _uiState.update { currentState ->
                currentState.copy(
                    patrimonioList = currentState.patrimonioList.map { if (it.patrimonioModel.id == id) newPatrimonioWithName else it },
                    patrimonioSelectedForEdit = null
                )
            }
            Log.w(AppTag, "Sucesso ao atulaizar")
        } catch (e: Exception) {
            Log.e(AppTag, "Erro ao atualizar: ${e.message}")
        }
    }

    /*FIIs */


    fun loadFiis() = viewModelScope.launch {
        val fiisList =
            fiiRepository.getFiisWithName(_uiState.value.monthYear).firstOrNull() ?: emptyList()
        _uiState.update { currentState ->
            currentState.copy(fiisList = fiisList)
        }
    }

    fun selectFiiForEdit(fiiWithName: FiiWithName) {

        if (uiState.value.fiiSelectedForEdit != null) {
            _uiState.update { currentState ->
                currentState.copy(
                    fiiSelectedForEdit = null
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    fiiSelectedForEdit = fiiWithName,
                    fiiCotas = fiiWithName.fii.cotas.toString(),
                    fiiProventos = fiiWithName.fii.proventos.toString()
                )
            }
        }

    }

    fun updateFii() = viewModelScope.launch {
        val fiiWithName = uiState.value.fiiSelectedForEdit!!
        val fiiModelId = fiiWithName.fii.id
        val fiisNameId = fiiWithName.fii.id_fiis_name
        val cotas = uiState.value.fiiCotas
        val mesAno = uiState.value.monthYear
        val proventos = uiState.value.fiiProventos

        var v = 0.0f
        if (proventos.isNotEmpty()) {
            v = proventos.toFloat() / 100
        }

        val newFiiModel =
            FiModel(fiiModelId, fiisNameId, cotas.toInt(), mesAno, v)

        val newFiiWithName = FiiWithName(newFiiModel, fiiWithName.fiisName)

        try {
            fiiRepository.update(newFiiModel)
            _uiState.update { currentState ->
                currentState.copy(
                    fiisList = currentState.fiisList.map { if (it.fii.id == fiiModelId) newFiiWithName else it },
                    fiiSelectedForEdit = null
                )
            }
            Log.w(AppTag, "Sucesso ao atulaizar")
        } catch (e: Exception) {
            Log.e(AppTag, "Erro ao atualizar: ${e.message}")
        }
    }

    fun insertFiiName() = viewModelScope.launch {
        val id = fiisNameRepository.insert(FiisNamesModel(nome = _uiState.value.fiiName))
        // Loop para criar registros para os próximos 360 meses (30 anos)
        for (i in 0 until 360) {
            val mesAno = getMesAno(i) // Gera o mês e ano com base no incremento
            fiiRepository.insert(
                FiModel(
                    id_fiis_name = id,
                    cotas = 0,
                    mes_ano = mesAno
                )
            )
        }

        _uiState.update { currentState ->
            currentState.copy(
                inputFiiNameIsVisible = false,
                reloadScreen = true
            )
        }
    }

    fun onChangeFiiName(text: String) {
        _uiState.update { it.copy(fiiName = text) }
    }

    fun onChangeCotas(text: String) {
        _uiState.update { it.copy(fiiCotas = text) }
    }

    fun onChangeProventos(text: String) {
        _uiState.update { it.copy(fiiProventos = text) }
    }

    fun toggleAddFiiInputVisibility() {
        _uiState.update { it.copy(inputFiiNameIsVisible = !it.inputFiiNameIsVisible) }
    }


    fun toggleMonthListVisibility() {
        _uiState.update { it.copy(monthListVisible = !it.monthListVisible) }
    }

    fun toggleMenuListVisibility() {
        _uiState.update { it.copy(menuListVisible = !it.menuListVisible) }
    }

    fun toggleBalanceVisibility() {
        _uiState.update { it.copy(balanceIsVisible = !it.balanceIsVisible) }
    }


}
