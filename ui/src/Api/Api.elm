module Api.Api exposing (getTransactions) 

import Http
import Json.Decode as Decode exposing (Decoder)
import Model.ProgramTypes as PT exposing (Msg)
import Model.DomainTypes exposing (Transaction, Raider)
import Api.Decoders exposing (decodeTransaction, decodeRaider)
import Api.Encoders exposing (raiderEncoder)

getTransactions : () -> Cmd Msg
getTransactions _ =
    let
        url = "/api/v1/transactions"
        request = Http.get url decodeTransactions
    in
        Http.send PT.NewTransactions request

decodeTransactions: Decoder (List Transaction)
decodeTransactions =
    Decode.list decodeTransaction

saveRaider: Raider -> Cmd Msg 
saveRaider raider =
    let
        url = "/api/v1/raider"
        body = Http.jsonBody (raiderEncoder raider)
        request = Http.post url body decodeRaider
    in
        Http.send PT.NewRaider request