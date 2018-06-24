module Model.ModelFuncs exposing (..)

import Model.DomainTypes exposing (..)

transgressionString: Transgression -> String
transgressionString transgression = 
    case transgression of
        AfkNoWarning -> "AFK Without Warning"
        PulledBoss -> "Pulled Boss"
        PulledAdds -> "Pulled Adds"
        FailedLearnedMechanic -> "Failed a Known Mechanic"
        AfkTooLong -> "AFKed for too long"
        OpenMic -> "Mic Open with Noisy Background"
        WasADick -> "Was a Dick"
        NoRaidMats -> "Came to Raid Wihout Mats"
        LateForRaid -> "Was late for Raid without warning"
        DidntRepair -> "Didnt repair before raid"

instanceString: Instance -> String
instanceString instance =
    case instance of
        Ruins25 -> "AQ Ruins 25"
        Ruins10 -> "AQ Ruins 10"
        Temple10 -> "AQ Temple 10"
        Temple25 -> "AQ Temple 25"
        Mc10 -> "Molten Core 10"
        Mc25 -> "Molten Core 25"
        Zg10 -> "Zul'Gurub 10"
        Zg25 -> "Zul'Gurub 25"
        Bwl10 -> "Black Wing Layer 10"
        Bwl25 -> "Black Wing Layer 25"
        Ony10 -> "Onyxia 10"
        Ony25 -> "Onyxia 25"
            