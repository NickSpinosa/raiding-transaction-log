module Model.ProgramTypes exposing (..)

import Http
import Model.DomainTypes exposing(Transaction, Raider)

type alias Transactions = List Transaction

type alias Model =
    { transactions: Transactions
    }

type Msg
    = GetTransactions
    | NewTransactions (Result Http.Error Transactions)
    | NewRaider (Result Http.Error Raider)
