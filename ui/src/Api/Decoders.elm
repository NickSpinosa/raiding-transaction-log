module Api.Decoders exposing (..)

import Model.DomainTypes exposing (..)
import Json.Decode as Decode exposing (Decoder, field, map2, map4, map6, andThen)

decodeRaider: Decoder Raider
decodeRaider = 
    map2 Raider
        (field "id" Decode.int)
        (field "name" Decode.string)

decodeRaid: Decoder Raid
decodeRaid =
    map4 Raid
        (field "id" Decode.int)
        (field "instance" instanceDecoder)
        (field "date" Decode.int)
        (field "raidLeader" decodeRaider)

decodeAmount: Decoder Amount
decodeAmount =
    map4 Amount
        (field "id" Decode.int)
        (field "gold" Decode.int)
        (field "silver" Decode.int)
        (field "copper" Decode.int)

decodeTransaction: Decoder Transaction
decodeTransaction = 
    map6 Transaction
        (field "id" Decode.int)
        (field "raid" decodeRaid)
        (field "raider" decodeRaider)
        (field "cost" decodeAmount)
        (field "reason" transgressionDecoder)
        (field "detail" (Decode.maybe Decode.string))

instanceDecoder: Decoder Instance
instanceDecoder =
    Decode.string
        |> andThen (\str ->
            case str of
                "RUINS_25" -> Decode.succeed Ruins25           
                "RUINS_10" -> Decode.succeed Ruins10
                "TEMPLE_10" -> Decode.succeed Temple10
                "TEMPLE_25" -> Decode.succeed Temple25
                "MC_10" -> Decode.succeed Mc10
                "MC_25" -> Decode.succeed Mc25
                "ZG_10" -> Decode.succeed Zg10
                "ZG_25" -> Decode.succeed Zg25
                "BWL_10" -> Decode.succeed Bwl10
                "BWL_25" -> Decode.succeed Bwl25
                "ONY_10" -> Decode.succeed Ony10
                "ONY_25" -> Decode.succeed Ony25
                somethingElse -> 
                    Decode.fail <| "Unknown instance: " ++ somethingElse
        )

transgressionDecoder: Decoder Transgression
transgressionDecoder =
    Decode.string
        |> andThen (\str ->
            case str of
                "AFK_NO_WARNING" -> Decode.succeed AfkNoWarning           
                "PULLED_BOSS" -> Decode.succeed PulledBoss
                "PULLED_ADDS" -> Decode.succeed PulledAdds
                "FAILED_LEARNED_MECHANIC" -> Decode.succeed FailedLearnedMechanic
                "AFK_TOO_LONG" -> Decode.succeed AfkTooLong
                "OPEN_MIC" -> Decode.succeed OpenMic
                "WAS_A_DICK" -> Decode.succeed WasADick
                "NO_RAID_MATS" -> Decode.succeed NoRaidMats
                "LATE_FOR_RAID" -> Decode.succeed LateForRaid
                "DIDNT_REPAIR" -> Decode.succeed DidntRepair
                somethingElse -> 
                    Decode.fail <| "Unknown transgression: " ++ somethingElse
        )