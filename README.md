## 배경
멋쟁이사자들처럼이 주최한 AI 창업가정신 부트캠프에 `쏘큅먼트` 팀으로 참가했습니다.

`중장비 대여 솔루션`의 MVP Android 앱을 이틀동안 개발했습니다.

<br>

## 기능 소개

|<img src="https://github.com/user-attachments/assets/6d760807-d21a-4f2a-ae47-74315be30a5d" width = 200>|<img src="https://github.com/user-attachments/assets/89e7a173-214f-4dd2-897b-529d4d1d6cd1" width = 200>|<img src="https://github.com/user-attachments/assets/00322bf4-f34a-4090-9655-b6a10b09a45b" width = 200>|<img src="https://github.com/user-attachments/assets/add8ae34-f20e-45fb-9b82-8516c0c806af" width = 200>|
|:-:|:-:|:-:|:-:|
|이용시간 설정|중장비 목록 보기|예약 및 결제|실시간 트래킹|

<br>

## 고려사항

**데이터**

모든 데이터는 `mock data`입니다. 그래서 FakeRepository로 mock data를 제공했습니다.
```kotlin
class FakePaymentRepositoryImpl @Inject constructor() : PaymentRepository {
    override fun rental(rentalInfo: RentalInfo) {
    }

    override fun getRandomTrackingInfo(): TrackingInfo {
        return TrackingInfo(
            rpm = (600..4000).random(),
            fuel = (0..600).random(),
            fuelEfficiency = (30..100).random() / 100f,
            workEfficiency = (30..100).random() / 100f
        )
    }
}
```
대신 시연 단계에서 실제 데이터를 사용한다는 느낌을 주기 위해 `TrakingInfo`를 랜덤값으로 제공했습니다.

<br><br>

**아키텍처**

`클린 아키텍처`로 프로젝트를 구성했습니다.

presentation 모듈은 data 모듈의 정체를 모릅니다. domain 모듈의 리소스만 사용하기 때문입니다.

현재는 서버와 연결이 없는 MVP 앱이지만, 이후 서버 데이터를 받을 때 data 모듈의 기능만 추가하면 되는 구조로 설계했습니다.

<br><br>

**애니메이션**

심미성을 위해 가격과 차트에 애니메이션을 추가했습니다.
```kotlin
@Composable
fun CircleChart(progress: Float) {
    val progressState = remember { mutableFloatStateOf(0f) }
    val progressAnim = animateFloatAsState(
        targetValue = progressState.value,
        label = "progress-anim"
    )
    LaunchedEffect(Unit) {
        delay(300)
        progressState.value += progress
    }
    CircularProgressIndicator(
        modifier = Modifier.size(80.dp),
        progress = { progressAnim.value },
        color = PrimaryColor,
        trackColor = Color.LightGray,
        strokeWidth = 5.dp
    )
}
```
특히 차트는 0%에서 target% 까지 변화하는 효과를 주고 싶어 `MutableState`, `AnimateState`, `LaunchedEffect`를 조합해 구현했습니다.
