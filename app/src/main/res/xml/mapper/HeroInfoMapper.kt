package xml.mapper

import com.levp.immersivedotastats.data.remote.dto.heroinfo.HeroStatsInfoItem
import com.levp.immersivedotastats.data.remote.dto.heroinfo.HeroInfoViewEntity
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoEntity

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

    fun mapListForDB(list: List<HeroStatsInfoItem>): List<HeroInfoEntity> =
        list.map { heroStats ->
            HeroInfoEntity(
                id = heroStats.hero_id,
                heroId = heroStats.hero_id,
                heroName = heroStats.localized_name,
                primaryAttribute = heroStats.primary_attr,
                localImage = heroStats.img,
                legs = heroStats.legs,
            )
        }
}