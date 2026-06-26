# Contributing to OmniPaper

## Project structure

OmniPaper is a patcher fork of Paper using `io.papermc.paperweight.patcher`.
**We do not store Paper's source code.** All patches apply on top of Paper's
upstream at the commit pinned in `gradle.properties`.

## Patch tiers

- `omnipaper-server/minecraft-patches/` — diffs against NMS (`net/minecraft/`, `com/mojang/`)
- `omnipaper-server/paper-patches/` — diffs against CraftBukkit/Paper (`org/bukkit/craftbukkit/`, `io/papermc/`)
- `omnipaper-api/paper-patches/` — diffs against `paper-api`

OmniPaper's own new classes live in:
- `omnipaper-server/src/main/java/io/github/omnipaper/server/`
- `omnipaper-api/src/main/java/io/github/omnipaper/api/`

Patches reference these classes by fully-qualified name (e.g. `io.github.omnipaper.server.config.OmniPaperConfigurations.get()`).

## Setup

```bash
git clone https://github.com/your-org/OmniPaper
cd OmniPaper
./gradlew applyPatches
```

## Patch comments

Every OmniPaper change must be wrapped in start/end comments:
```java
// OmniPaper start - description
... changed code ...
// OmniPaper end - description
```

When porting a patch from another project, keep the upstream attribution header
and add an OmniPaper attribution line below it.
