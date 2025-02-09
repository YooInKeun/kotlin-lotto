package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("로또 테스트")
class LottoTest {

    private val winningNumbers = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.valueOf(it) })
    private val bonusNumber = LottoNumber.valueOf(45)
    private val drawNumbers = DrawNumbers(winningNumbers, bonusNumber)

    @Test
    fun `당첨 번호 6개가 일치하면 1등`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.FIRST_PRIZE)
    }

    @Test
    fun `당첨 번호 5개와 보너스 볼이 일치하면 2등`() {
        // given
        val lotto = Lotto(listOf(2, 3, 4, 5, 6, 45).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.SECOND_PRIZE)
    }

    @Test
    fun `당첨 번호 5개가 일치하면 3등`() {
        // given
        val lotto = Lotto(listOf(2, 3, 4, 5, 6, 7).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.THIRD_PRIZE)
    }

    @Test
    fun `당첨 번호 4개가 일치하면 4등`() {
        // given
        val lotto = Lotto(listOf(3, 4, 5, 6, 7, 8).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.FOURTH_PRIZE)
    }

    @Test
    fun `당첨 번호 3개가 일치하면 5등`() {
        // given
        val lotto = Lotto(listOf(4, 5, 6, 7, 8, 9).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.FIFTH_PRIZE)
    }

    @Test
    fun `일치하는 당첨 번호가 2개면 꽝`() {
        // given
        val lotto = Lotto(listOf(5, 6, 7, 8, 9, 10).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.NONE)
    }

    @Test
    fun `일치하는 당첨 번호가 1개면 꽝`() {
        // given
        val lotto = Lotto(listOf(6, 7, 8, 9, 10, 11).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.NONE)
    }

    @Test
    fun `일치하는 당첨 번호가 0개면 꽝`() {
        // given
        val lotto = Lotto(listOf(7, 8, 9, 10, 11, 12).map { LottoNumber.valueOf(it) })

        // when, then
        assertEquals(lotto.findRank(drawNumbers), WinningRank.NONE)
    }

    @Test
    fun `중복되는 로또 번호가 있으면 예외 발생`() {
        // given, when, then
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 1, 2, 3, 4, 5).map { LottoNumber.valueOf(it) })
        }
        assertEquals("로또에 중복되는 번호가 있을 수 없습니다.", exception.message)
    }

    @Test
    fun `로또 번호 개수가 6개가 아니면 예외 발생`() {
        // given, when, then
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5).map { LottoNumber.valueOf(it) })
        }
        assertEquals("로또 번호 개수는 6개 이어야 합니다.", exception.message)
    }
}
