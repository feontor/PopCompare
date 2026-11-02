package com.fedor.populationguesser

object Countries {

    val easyCountries = listOf(
        Country("🇨🇳 China", 1_422_584_933, Difficulty.EASY),
        Country("🇮🇳 India", 1_438_069_596, Difficulty.EASY),
        Country("🇺🇸 United States", 343_477_335, Difficulty.EASY),
        Country("🇮🇩 Indonesia", 281_190_067, Difficulty.EASY),
        Country("🇵🇰 Pakistan", 247_504_495, Difficulty.EASY),
        Country("🇳🇬 Nigeria", 227_882_945, Difficulty.EASY),
        Country("🇧🇷 Brazil", 211_140_729, Difficulty.EASY),
        Country("🇧🇩 Bangladesh", 171_466_990, Difficulty.EASY),
        Country("🇷🇺 Russia", 145_440_500, Difficulty.EASY),
        Country("🇲🇽 Mexico", 129_739_759, Difficulty.EASY),
        Country("🇯🇵 Japan", 124_370_947, Difficulty.EASY),
        Country("🇪🇹 Ethiopia", 128_691_692, Difficulty.EASY),
        Country("🇵🇭 Philippines", 114_891_199, Difficulty.EASY),
        Country("🇪🇬 Egypt", 114_535_772, Difficulty.EASY),
        Country("🇩🇪 Germany", 84_548_231, Difficulty.EASY)
    )

    val mediumCountries = listOf(
        Country("🇻🇳 Vietnam", 100_352_192, Difficulty.MEDIUM),
        Country("🇮🇷 Iran", 90_608_707, Difficulty.MEDIUM),
        Country("🇹🇷 Turkey", 87_270_501, Difficulty.MEDIUM),
        Country("🇹🇭 Thailand", 71_702_435, Difficulty.MEDIUM),
        Country("🇬🇧 United Kingdom", 68_682_962, Difficulty.MEDIUM),
        Country("🇹🇿 Tanzania", 66_617_606, Difficulty.MEDIUM),
        Country("🇫🇷 France", 66_438_822, Difficulty.MEDIUM),
        Country("🇿🇦 South Africa", 63_212_384, Difficulty.MEDIUM),
        Country("🇮🇹 Italy", 59_499_453, Difficulty.MEDIUM),
        Country("🇰🇪 Kenya", 55_339_003, Difficulty.MEDIUM),
        Country("🇲🇲 Myanmar", 54_133_798, Difficulty.MEDIUM),
        Country("🇨🇴 Colombia", 52_321_152, Difficulty.MEDIUM),
        Country("🇰🇷 South Korea", 51_748_739, Difficulty.MEDIUM),
        Country("🇪🇸 Spain", 47_911_579, Difficulty.MEDIUM),
        Country("🇺🇬 Uganda", 48_656_601, Difficulty.MEDIUM)
    )

    val hardCountries = listOf(
        Country("🇸🇰 Slovakia", 5_518_055, Difficulty.HARD),
        Country("🇫🇮 Finland", 5_601_185, Difficulty.HARD),
        Country("🇳🇴 Norway", 5_519_167, Difficulty.HARD),
        Country("🇮🇪 Ireland", 5_196_630, Difficulty.HARD),
        Country("🇳🇿 New Zealand", 5_172_836, Difficulty.HARD),
        Country("🇨🇷 Costa Rica", 5_105_525, Difficulty.HARD),
        Country("🇭🇷 Croatia", 3_896_023, Difficulty.HARD),
        Country("🇱🇹 Lithuania", 2_854_099, Difficulty.HARD),
        Country("🇸🇮 Slovenia", 2_118_396, Difficulty.HARD),
        Country("🇱🇻 Latvia", 1_882_396, Difficulty.HARD),
        Country("🇲🇰 North Macedonia", 1_831_802, Difficulty.HARD),
        Country("🇪🇪 Estonia", 1_367_196, Difficulty.HARD),
        Country("🇨🇾 Cyprus", 1_344_976, Difficulty.HARD),
        Country("🇲🇪 Montenegro", 633_552, Difficulty.HARD),
        Country("🇱🇺 Luxembourg", 665_098, Difficulty.HARD)
    )

    fun getByDifficulty(difficulty: Difficulty): List<Country> {
        return when (difficulty) {
            Difficulty.EASY -> easyCountries
            Difficulty.MEDIUM -> mediumCountries
            Difficulty.HARD -> hardCountries
        }
    }
}