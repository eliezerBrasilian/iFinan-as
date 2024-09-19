package com.br.ifinancas.ui.screens.home

import com.br.ifinancas.db.models.FiModel.FiiWithName
import com.br.ifinancas.db.models.PatrimonioModel.PatrimonioWithName

data class HomeUiState(
    val monthSelected: String = "",
    val monthYear: String = "",
    val monthListVisible: Boolean = false,
    val popUpSponsorVisible: Boolean = false,
    val menuListVisible: Boolean = false,
    val balanceIsVisible: Boolean = true,
    val fiiName: String = "",
    val inputFiiNameIsVisible: Boolean = false,
    val fiiSelectedForEdit: FiiWithName? = null,
    val fiiCotas: String = "",
    val fiiProventos: String = "",
    val fiisList: List<FiiWithName> = emptyList(),
    val reloadScreen: Boolean = false,
    val patrimonioList: List<PatrimonioWithName> = emptyList(),
    val patrimonioName: String = "",
    val inputPatrimonioNameIsVisible: Boolean = false,
    val patrimonioTotalInput: String = "",
    val patrimonioSelectedForEdit: PatrimonioWithName? = null,
    val patrimonioTotalCount: Float = 0f,
    val popupAddRegisterIsVisible: Boolean = false
)