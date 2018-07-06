module Api.Api exposing (..) 

import Http
import Json.Decode as Decode exposing (Decoder)
import Model.ProgramTypes as PT exposing (Msg)
import Model.DomainTypes exposing (Transaction)
import Api.Decoders exposing (decodeTransaction)

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
