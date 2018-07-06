module Api.Encoders exposing (..)

import Json.Encode exposing (object, Value, int, string, null)
import Model.DomainTypes exposing (..)
import Model.ModelFuncs exposing (..)

amountEncoder: Amount -> Value
amountEncoder amount = 
    object [ ("id", int amount.id) 
           , ("gold", int amount.id)
           , ("silver", int amount.id)
           , ("copper", int amount.id)
           ]
        
raiderEncoder: Raider -> Value
raiderEncoder raider =
    object [ ("id", int raider.id)
           , ("name", string raider.name)
           ]

raidEncoder: Raid -> Value
raidEncoder raid =
    object [ ("id", int raid.id)
           , ("date", int raid.date)
           , ("instance", string (instanceString raid.instance))
           , ("raidLeader", raiderEncoder raid.raidLeader)
           ]

transactionEncoder: Transaction -> Value
transactionEncoder transaction =
    let
        detail = case transaction.detail of
            Nothing -> null
            Just str -> string str
                
    in
        object [ ("id", int transaction.id)
               , ("raid", raidEncoder transaction.raid)
               , ("raider", raiderEncoder transaction.raider)
               , ("cost", amountEncoder transaction.cost)
               , ("reason", string (transgressionString transaction.reason))
               , ("detail", detail)
               ]