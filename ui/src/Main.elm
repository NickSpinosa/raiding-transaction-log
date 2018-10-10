module Main exposing (..)

import Html exposing (Html, text, div, h1, img)
import Platform.Cmd as Cmd
import View.TransactionList exposing(transactionTable)
import Model.ProgramTypes exposing(..)
import Api.Api exposing (getTransactions)

---- MODEL ----

init : ( Model, Cmd Msg )
init =
    ( { transactions = []}, getTransactions ())


---- UPDATE ----

update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        GetTransactions -> ( model, getTransactions () )
        NewTransactions (Ok trans) -> 
            ( { model | transactions = trans}, Cmd.none )
        NewTransactions (Err _) ->
            (model, Cmd.none)


---- VIEW ----

view : Model -> Html Msg
view model =
    transactionTable model.transactions


---- PROGRAM ----

main : Program Never Model Msg
main =
    Html.program
        { view = view
        , init = init
        , update = update
        , subscriptions = always Sub.none
        }
