module Model.DomainTypes exposing (..)

type Instance
    = Ruins25
    | Ruins10
    | Temple10
    | Temple25
    | Mc10
    | Mc25
    | Zg10
    | Zg25
    | Bwl10
    | Bwl25
    | Ony10
    | Ony25

type Transgression
    = AfkNoWarning
    | PulledBoss
    | PulledAdds
    | FailedLearnedMechanic
    | AfkTooLong
    | OpenMic
    | WasADick
    | NoRaidMats
    | LateForRaid
    | DidntRepair

type alias Amount = 
    { id: Int
    , gold: Int
    , silver: Int
    , copper: Int
    }

type alias Raider =
    { id: Int
    , name: String
    }

type alias Raid =
    { id: Int
    , instance: Instance
    , date: Int
    , raidLeader: Raider
    }

type alias Transaction =
    { id: Int
    , raid: Raid
    , raider: Raider
    , cost: Amount
    , reason: Transgression
    , detail: Maybe String
    }