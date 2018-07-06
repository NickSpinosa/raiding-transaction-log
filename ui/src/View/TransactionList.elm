module View.TransactionList exposing (transactionTable)

import Model.DomainTypes exposing (Transaction, Raider, Raid, Transgression, Amount, Instance)
import Model.ModelFuncs exposing (transgressionString, instanceString)
import Html exposing (Html, text, div, ul, li)
import List exposing (map)

transactionRow: Transaction -> Html msg
transactionRow transaction =
    let
        raiderName = transaction.raider.name
        transgression = transgressionString transaction.reason
        instance = instanceString transaction.raid.instance
        -- raidDate = transaction.raid.date
        amount = transaction.cost
    in
        div
            []
            [ div [] [text raiderName] 
            , div [] [text transgression]
            , div [] [text instance]
            -- , div [] [text raidDate]
            ]

transactionTable: List Transaction -> Html msg
transactionTable transactions = 
    let
        headers =
            li 
                []
                [ div [] [text "Raider"]
                , div [] [text "Transgression"]
                , div [] [text "Instance"]] 
        rows = headers :: (map transactionRow transactions)
    in
        ul [] rows