package lotto.domain.model

import lotto.domain.model.vo.LottoNumber
import lotto.domain.model.vo.LottoNumbers


/**
 * 로또 당첨 번호
 * */
data class WinningLottoNumbers(val winningLottoNumbers: LottoNumbers, val winningBonusNumber: LottoNumber) {

    companion object {
        fun of(winningLottoNumbers: LottoNumbers, winningBonusNumber: LottoNumber): WinningLottoNumbers = WinningLottoNumbers(winningLottoNumbers, winningBonusNumber)
    }
}
