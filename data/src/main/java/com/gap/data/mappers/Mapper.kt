package com.gap.data.mappers

import com.gap.data.network.dto.CurrencyDto
import com.gap.data.network.dto.ValuteInfo
import com.gap.domain.entities.Currency
import com.gap.domain.entities.Valute
import java.text.SimpleDateFormat
import java.util.TimeZone
import kotlin.math.pow
import kotlin.math.roundToInt


object Mapper {
    fun dtoToEntity(dto: CurrencyDto): Currency {
        val valutes = mutableListOf<Valute>()
        val valuteList = listOf(
            dto.valute.aud,
            dto.valute.azn,
            dto.valute.gbp,
            dto.valute.amd,
            dto.valute.byn,
            dto.valute.bgn,
            dto.valute.brl,
            dto.valute.huf,
            dto.valute.vnd,
            dto.valute.hkd,
            dto.valute.gel,
            dto.valute.dkk,
            dto.valute.aed,
            dto.valute.usd,
            dto.valute.eur,
            dto.valute.egp,
            dto.valute.inr,
            dto.valute.idr,
            dto.valute.kzt,
            dto.valute.cad,
            dto.valute.qar,
            dto.valute.kgs,
            dto.valute.cny,
            dto.valute.mdl,
            dto.valute.nzd,
            dto.valute.nok,
            dto.valute.pln,
            dto.valute.ron,
            dto.valute.xdr,
            dto.valute.sgd,
            dto.valute.tjs,
            dto.valute.thb,
            dto.valute.TRY,
            dto.valute.tmt,
            dto.valute.uzs,
            dto.valute.uah,
            dto.valute.czk,
            dto.valute.sek,
            dto.valute.chf,
            dto.valute.rsd,
            dto.valute.zar,
            dto.valute.krw,
            dto.valute.jpy
        )

        for (valute in valuteList) {
            valutes.add(infoToEntity(valute))
        }
        return Currency(
            timestamp = convertDateTime(dto.timestamp),
            valute = valutes
        )
    }

    private fun infoToEntity(info: ValuteInfo): Valute {
        return Valute(
            id = info.id,
            charCode = info.charCode,
            nominal = info.nominal,
            name = info.name,
            value = info.value.roundTo(2),
            previous = info.previous,
            difference = (info.value - info.previous).roundTo(2)
        )
    }

    private fun Double.roundTo(precision: Int): Double {
        val scale = 10.0.pow(precision)
        return (this * scale).roundToInt() / scale
    }

    @Suppress("SimpleDateFormat")
    private fun convertDateTime(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        val outputFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")

        val date = inputFormat.parse(dateTimeString)
        outputFormat.timeZone = TimeZone.getDefault()
        return outputFormat.format(date)
    }
}

