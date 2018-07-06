module Api.Decoders exposing (..)

import Model.DomainTypes exposing (..)
import Json.Decode exposing (Decoder, field, map2)

decodeRaider: Decoder Raider
decodeRaider = 
    map2 Raid
        (field "id" Decode.int)
        (filed "name" Decode.string)

decodeRaid: Decode Raid
decodeRaid =
    map4 Raider
        (field "id" Decode.int)
        -- (field "instance" Instance)
        (field "date" Decode.int)
        (field "raidLeader" Raider)

decodeTransaction: Decode Transaction
decodeTransaction = 
