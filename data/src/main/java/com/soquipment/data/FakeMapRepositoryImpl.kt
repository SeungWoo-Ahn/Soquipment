package com.soquipment.data

import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment
import com.soquipment.domain.model.Point
import com.soquipment.domain.repository.MapRepository
import javax.inject.Inject

class FakeMapRepositoryImpl @Inject constructor() : MapRepository {
    private val companyList = listOf(
        Company(
            name = "광화문 중장비 렌탈",
            point = Point(lat = "37.5700", lng = "126.9790")
        ),
        Company(
            name = "서울 크레인 서비스",
            point = Point(lat = "37.5715", lng = "126.9775")
        ),
        Company(
            name = "종로 건설 장비 대여",
            point = Point(lat = "37.5725", lng = "126.9800")
        ),
        Company(
            name = "한강 중장비 리스",
            point = Point(lat = "37.5730", lng = "126.9780")
        ),
        Company(
            name = "대한 건설 렌탈",
            point = Point(lat = "37.5710", lng = "126.9765")
        )
    )
    private val companyMap = hashMapOf<Company, List<Equipment>>().apply {
        put(
            companyList[0],
            listOf(
                Equipment("소형 굴삭기", "https://www.volvoce.com/-/media/aprimo/images/excavators/ecr18e/volvo-find-compact-excavator-ecr18e-1000x1000.avif?mw=416&v=oodsPw&f=avif&q=64&hash=E989D7A34E4100EBB0B948FEF9EDE6E9", "볼보", 94615, 26120),
                Equipment("지게차", "https://www.volvoce.com/-/media/aprimo/images/compact-excavators/ew60e/volvo-find-compact-excavator-ew60epro-t4f-1000x1000.avif?mw=512&v=vAhtPw&f=avif&q=64&hash=9616A1C4A5C6C14BFF1331D3FF727583", "현대HD", 75328, 21073),
                Equipment("전동 물류장비", "https://www.doosanbobcat.com/images/sub/industrial_item01_topimg.png", "두산밥캣", 84542, 20688),
                Equipment("지게차", "https://www.volvoce.com/-/media/aprimo/images/excavators/ecr18e/volvo-find-compact-excavator-ecr18e-1000x1000.avif?mw=416&v=oodsPw&f=avif&q=64&hash=E989D7A34E4100EBB0B948FEF9EDE6E9", "볼보", 60476, 27751),
                Equipment("전동 물류장비", "https://cdn.imweb.me/thumbnail/20230508/8d2ce7603d3a1.jpg", "두산밥캣", 72611, 18668),
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/industrial_item05_topimg.png", "볼보", 91674, 22488)
            )
        )
        put(
            companyList[1],
            listOf(
                Equipment("소형 굴삭기", "https://www.doosanbobcat.com/images/sub/compact_item02_topimg.png", "현대HD", 71330, 29097),
                Equipment("소형 굴삭기", "https://www.doosanbobcat.com/images/sub/industrial_item05_topimg.png", "볼보", 63512, 19386),
                Equipment("소형 굴삭기", "https://www.volvoce.com/-/media/aprimo/images/compact-excavators/ew60e/volvo-find-compact-excavator-ew60epro-t4f-1000x1000.avif?mw=512&v=vAhtPw&f=avif&q=64&hash=9616A1C4A5C6C14BFF1331D3FF727583", "볼보", 65461, 15987),
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/compact_item02_topimg.png", "볼보", 89277, 38759),
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/compact_item02_topimg.png", "두산밥캣", 82579, 33573),
                Equipment("소형 굴삭기", "https://www.doosanbobcat.com/images/sub/industrial_item05_topimg.png", "현대HD", 66149, 30614)
            )
        )
        put(
            companyList[2],
            listOf(
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/compact_item02_topimg.png", "현대HD", 80117, 20282),
                Equipment("소형 굴삭기", "https://www.doosanbobcat.com/images/sub/industrial_item01_topimg.png", "현대HD", 63503, 34959),
                Equipment("로더", "https://cdn.imweb.me/thumbnail/20230508/8d2ce7603d3a1.jpg", "현대HD", 69041, 21439),
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/industrial_item02_topimg.png", "두산밥캣", 69154, 37262),
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/industrial_item02_topimg.png", "두산밥캣", 82908, 36728)
            )
        )
        put(
            companyList[3],
            listOf(
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/industrial_item03_topimg.png", "현대HD", 71625, 18024),
                Equipment("전동 물류장비", "https://www.doosanbobcat.com/images/sub/industrial_item05_topimg.png", "두산밥캣", 87842, 31987),
                Equipment("소형 굴삭기", "https://www.volvoce.com/-/media/aprimo/images/excavators/ecr18e/volvo-find-compact-excavator-ecr18e-1000x1000.avif?mw=416&v=oodsPw&f=avif&q=64&hash=E989D7A34E4100EBB0B948FEF9EDE6E9", "볼보", 90915, 35678),
                Equipment("로더", "https://www.doosanbobcat.com/images/sub/compact_item01_topimg.png", "볼보", 74629, 24082),
                Equipment("소형 굴삭기", "https://www.doosanbobcat.com/images/sub/industrial_item02_topimg.png", "두산밥캣", 68732, 36521),
                Equipment("지게차", "https://cdn.imweb.me/thumbnail/20230508/8d2ce7603d3a1.jpg", "현대HD", 90427, 17239)
            )
        )
        put(
            companyList[4],
            listOf(
                Equipment("전동 물류장비", "https://www.doosanbobcat.com/images/sub/industrial_item05_topimg.png", "현대HD", 61532, 19024),
                Equipment("로더", "https://www.doosanbobcat.com/images/sub/compact_item01_topimg.png", "볼보", 99327, 34823),
                Equipment("소형 굴삭기", "https://www.volvoce.com/-/media/aprimo/images/compact-excavators/ew60e/volvo-find-compact-excavator-ew60epro-t4f-1000x1000.avif?mw=512&v=vAhtPw&f=avif&q=64&hash=9616A1C4A5C6C14BFF1331D3FF727583", "볼보", 86643, 21876),
                Equipment("지게차", "https://cdn.imweb.me/thumbnail/20230508/8d2ce7603d3a1.jpg", "현대HD", 68214, 40291),
                Equipment("지게차", "https://www.doosanbobcat.com/images/sub/industrial_item03_topimg.png", "두산밥캣", 81745, 38921)
            )
        )
    }

    override fun getCompanyList(): List<Company> {
        return companyList
    }

    override fun getEquipmentList(company: Company): List<Equipment> {
        return companyMap[company] ?: listOf()
    }
}