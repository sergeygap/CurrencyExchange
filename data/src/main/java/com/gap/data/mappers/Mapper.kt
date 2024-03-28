package com.gap.data.mappers

import com.gap.data.network.dto.CurrencyDto
import com.gap.data.network.dto.ValuteInfo
import com.gap.domain.entities.Currency
import com.gap.domain.entities.Valute
import java.text.SimpleDateFormat
import java.util.TimeZone


object Mapper {
    fun dtoToEntity(dto: CurrencyDto): Currency {
        val valutes = mutableListOf<Valute>()
        val valuteList = listOf(
            dto.valute.aed,
            dto.valute.amd,
            dto.valute.aud,
            dto.valute.azn,
            dto.valute.bgn,
            dto.valute.brl,
            dto.valute.byn,
            dto.valute.cad,
            dto.valute.chf,
            dto.valute.cny,
            dto.valute.czk,
            dto.valute.dkk,
            dto.valute.egp,
            dto.valute.eur,
            dto.valute.gbp,
            dto.valute.gel,
            dto.valute.hkd,
            dto.valute.huf,
            dto.valute.idr,
            dto.valute.inr,
            dto.valute.jpy,
            dto.valute.kgs,
            dto.valute.krw,
            dto.valute.kzt,
            dto.valute.mdl,
            dto.valute.nok,
            dto.valute.nzd,
            dto.valute.pln,
            dto.valute.qar,
            dto.valute.ron,
            dto.valute.rsd,
            dto.valute.sek,
            dto.valute.sgd,
            dto.valute.thb,
            dto.valute.tjs,
            dto.valute.tmt,
            dto.valute.TRY,
            dto.valute.uah,
            dto.valute.usd,
            dto.valute.uzs,
            dto.valute.vnd,
            dto.valute.xdr,
            dto.valute.zar
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
            value = info.value,
            previous = info.previous
        )
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

