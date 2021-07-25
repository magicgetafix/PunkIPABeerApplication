package com.magicgetafix.android.punkipabeerapplication.api.response

data class Beer (val id : Int,
                 val name : String?,
                 val tagline : String?,
                 val first_brewed : String?,
                 val description : String?,
                 val image_url : String?,
                 val abv : Number,
                 val ibu : Number,
                 val target_fg : Number,
                 val target_og : Number,
                 val ebc : Number,
                 val srm : Number,
                 val ph : Number,
                 val attenuation_level : Double,
                 val volume : Volume?,
                 val boil_volume : Boil_volume?,
                 val method : Method?,
                 val ingredients : Ingredients?,
                 val food_pairing : List<String>,
                 val brewers_tips : String?,
                 val contributed_by : String?)


