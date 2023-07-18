package com.levp.immersivedotastats.data

import com.levp.immersivedotastats.data.remote.dto.heroinfo.HeroStatsInfoItem
import com.levp.immersivedotastats.presentation.screens.heroesinfo.HeroInfoViewEntity

class HeroInfoMapper {

    fun mapList(list: List<HeroStatsInfoItem>): List<HeroInfoViewEntity> =
        list.map { heroStats ->
            HeroInfoViewEntity(
                id = heroStats.hero_id,
                locName = heroStats.localized_name,
                primaryAttribute = heroStats.primary_attr,
                roles = heroStats.roles,
                image = heroStats.img,
                legs = heroStats.legs
            )
        }
}