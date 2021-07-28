package com.magicgetafix.android.punkipabeerapplication

import com.magicgetafix.android.punkipabeerapplication.api.response.Beer

object AndroidTestDataFactory {

    fun getJapaneseGermanBeer(): Beer {
        return Beer(
        description =
            "Japanese citrus fruit intensifies the sour nature of this German classic.",
        name = "Berliner Weisse With Yuzu - B-Sides", image_url = "https://images.punkapi.com/v2/keg.png",
        tagline = "Japanese Citrus Berliner Weisse.",
        id = 1,
        food_pairing = arrayListOf(), abv = 5.0)
    }

    fun getGermanImperialBeer(): Beer {
        return Beer(
        description =
            "Imperial Wheat beer / Weizenbock brewed by a homesick German in leather trousers. Think banana bread, bubble gum and David Hasselhoff."
        ,name = "Rabiator",
        image_url = "https://images.punkapi.com/v2/keg.png",
        tagline = "Imperial Wheat Beer",
        id = 2,
        food_pairing = arrayListOf(), abv = 10.0)
    }

    fun getBelgianBeer(): Beer {
        return Beer(
        description =
            "Re-brewed as a spring seasonal, this beer – which appeared originally as an Equity Punk shareholder creation – retains its trademark spicy, fruity edge. A perfect blend of Belgian Saison and US IPA, crushed peppercorns and heather honey are also added to produce a genuinely unique beer."
        ,name = "Electric India",
        image_url = "https://images.punkapi.com/v2/6.png",
        tagline = "Vibrant Hoppy Saison.",
        id = 3,
        food_pairing = arrayListOf(), abv = 6.7)
    }

    fun getPaleAle(): Beer {

        return Beer(
        description =
            "A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves too much to resist. All that is even before we get onto the fact that there are no additives, preservatives, pasteurization or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist."
        ,name = "Trashy Blonde",
        image_url = "https://images.punkapi.com/v2/2.png",
        tagline = "You Know You Shouldn't.",
        id = 4,
        food_pairing = arrayListOf(), abv = 10.0)
    }

    fun getBeerLongDescription(): Beer {

        return Beer(
        description = "A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves too much to resist. All that is even before we get onto the fact that there are no additives, preservatives, pasteurization or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist. h" +
        "hbkjbsajnxans;jkansjnacsnc.ajcnncsa,nc.akcn.jancjaknckjancjknc.akncanc.ksdajncjsdnajkncadjnanckjnkaksoKAS;almxLMAXL ASDMNLASKNCXLKASSNCXLSKNAKCXNASLKCN sdjcnsjcbkjdsbcsdkcjbadsjcbkadsjbckjsdbcakjsbcjabsc kajsbckasbcsjakbscjbacksajbckjasbcksajcbksajbckjacbksjbc kasjcbaksjcbkjsabcskjacbskacjbsackjbsakcjbaskjcbaskjcbskjcbasjkcbkasjbc kjasbckajbcsjbcskajcbaskjcbskasbjcasxzbjkcbsakcjbsajkcbsakkhvgskvhgghkvvghjjvhgvgvhkvsdcgh aghv k gkd cka sgd kghsav kgxd akghds vkag dkg sa skgh " +
                "hgvhgkvsadkhgvdksa vhksadvldhsa bhlsadb lhsdbalhxbslbx dlshbdxlhjsbx dljhsb xlhsbdxlxshbxlhdsblhxb dlshbxdslhbx dlsbhxlshxb slhdxblahsbxljdhsbxalsdbhlshjbxdhlsbxlhjbxsalbdx ljhsbx ljhdbhjlbs lahxb dsljhbc dlhsbc lsdab cdhljsbchjdsb aljhdsbc alj"
        ,name = "Trashy Blonde",
        image_url = "https://images.punkapi.com/v2/2.png",
        tagline = "You Know You Shouldn't.",
        id = 5,
        food_pairing = arrayListOf(), abv = 9.0)


    }

    fun getBeerNullName(): Beer {

        return Beer(
        description = "description",
        name = null,
        image_url = "https://images.punkapi.com/v2/2.png",
        tagline = "tagline",
        id = 6,
        food_pairing = arrayListOf(), abv = 2.3)


    }

    fun getBeerBlankName(): Beer {
        return Beer(
            description = "description",
            name = "",
            image_url = "https://images.punkapi.com/v2/2.png",
            tagline = "tagline",
            id = 7,
            food_pairing = arrayListOf(), abv = 2.3)
    }
    fun getBeerNullDescription(): Beer {
        return Beer(
            description = null,
            name = "Name",
            image_url = "https://images.punkapi.com/v2/2.png",
            tagline = "tagline",
            id = 8,
            food_pairing = arrayListOf(), abv = 4.3)
    }
    fun getBeerEmptyDescription(): Beer {
        return Beer(
            description = "",
            name = "Name",
            image_url = "https://images.punkapi.com/v2/2.png",
            tagline = "tagline",
            id = 9,
            food_pairing = arrayListOf(), abv = 10.4)
    }
}