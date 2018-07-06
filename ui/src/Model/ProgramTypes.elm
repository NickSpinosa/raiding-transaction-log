module Model.ProgramTypes exposing (..)

import Http
import Model.DomainTypes exposing(Transaction)

type alias Transactions = List Transaction

type alias Model =
    { transactions: Transactions
    }

type Msg
    = GetTransactions
    | NewTransactions (Result Http.Error Transactions)
