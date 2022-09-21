# SurrealDB Mod

![MC](https://img.shields.io/badge/MC%20Version-1.12.2-green?style=plastic)
![FORGE](https://img.shields.io/badge/Forge%20Version-14.23.5.2860-orange?style=plastic)
![VERSION](https://img.shields.io/badge/Version-Alpha%200.0.2-blue?style=plastic)

A Minecraft mod that provides a SurrealDB server for other mods to interact and communicate with.

This mod automatically downloads the latest version of SurrealDB to "/mods/surrealdb" and starts it whenever the Minecraft client starts on PreInit.
Its main purpose is to allow easy inter-mod/-world/-network data structures without local files or mod dependencies.

> **Warning**
> This mod is in early development and its primary use is for the production of internal tools.

## Ways to integrate with this mod
- [ ] Add this mod as a dependency and use its infrastructure (jar dependency)
- [X] Add this mod as a dependency and use the SurrealDBData Package (jar dependency)
- [ ] Get a Reference Function per IMC (Only requires a C+P DataClass)
- [ ] Get a Reference Function per IMC (String req -> String res)
- [ ] Get the authentication details per IMC (REST/WebSocket)

> **Note**
> Only the checkmarked bullet points are actually implemented to a somewhat usable extend
