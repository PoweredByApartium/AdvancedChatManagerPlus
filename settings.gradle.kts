rootProject.name = "AdvancedChatManagerPlus"
include("api")
include("paper")
include("impl")
include("impl:mongodb")
findProject(":impl:mongodb")?.name = "mongodb"
include("impl:sql")
findProject(":impl:sql")?.name = "sql"
include("impl:flatfile")
findProject(":impl:flatfile")?.name = "flatfile"
