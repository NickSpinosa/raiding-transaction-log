module Api.Transactions exposing (..) 

import Http
import Json.Decode exposing (Decoder)
import Model.ProgramTypes exposing (Msg)

geTransactions : () -> Cmd Msg
geTransactions _ =
    let
        url = "/api/v1/Transactions"
        request = Http.get url decodeTransactions
    in
        Http.send NewTransactions request

decodeTransactions: Decoder (List Transaction)
decodeTransactions =
    Decode.List

decodeTransaction: Decoder Transaction
decodeTransactions =