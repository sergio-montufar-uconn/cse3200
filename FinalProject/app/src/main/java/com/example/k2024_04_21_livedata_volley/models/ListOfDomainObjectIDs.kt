package  com.example.k2024_04_21_livedata_volley.models

object ListOfDomainObjectIDs {
    val allIds = mutableListOf<DomainObjectId>()

    fun getAllMyIds() : List<DomainObjectId> {
        return allIds
    }

    fun size() : Int {
        return allIdInts.size
    }

    val allIdInts = listOf<Int>(
        34,
        37,
        38,
        39,
        40,
        41,
        42,
        43,
        44,
        45,
        46,
        47,
        50,
        51,
        52,
        53,
        56,
        57,
        62,
        63,
        64,
        65,
        68,
        69,
        70,
        71,
        74,
        75,
        78,
        79,
        80,
        81,
        86,
        87,
        88,
        89,
        90,
        91,
        92,
        93,
        94,
        95,
        100,
        101,
        102,
        103,
        104,
        105,
        106,
        107,
        108,
        109,
        110,
        111,
        112,
        113,
        114,
        115,
        116,
        117,
        119,
        122,
        123,
        124,
        125,
        126,
        127,
        128,
        130,
        134,
        137,
        139,
        140,
        143,
        145,
        146,
        147,
        148,
        151,
        154,
        166,
        170,
        171,
        172,
        173,
        174,
        177,
        179,
        180,
        182,
        183,
        184,
        185,
        186,
        187,
        188,
        189,
        190,
        191,
        192,
        193,
        195,
        199,
        200,
        201,
        202,
        203,
        204,
        205,
        208,
        209,
        211,
        212,
        213,
        214,
        215,
        216,
        218,
        219,
        220,
        221,
        223,
        224,
        225,
        227,
        229,
        230,
        231,
        232
    )

    init{
        allIdInts.forEach{ myInt ->
            allIds.add(DomainObjectId(myInt))
        }
    }

}