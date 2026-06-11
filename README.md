# OmniPaper

A Paper fork for Minecraft 1.21.11 — heavily optimized, plugin-compatible.

## Build system

OmniPaper uses `io.papermc.paperweight.patcher` — the same patcher architecture as [Leaf](https://github.com/Winds-Studio/Leaf) and [Purpur](https://github.com/PurpurMC/Purpur). It stores only patches and its own source; all of Paper's source is pulled at build time via the `paperCommit` property.

```
OmniPaper/
├── build.gradle.kts                  # paperweight.patcher root
├── gradle.properties                 # version + paperCommit
├── omnipaper-api/
│   ├── build.gradle.kts.patch        # patches Paper's API build file
│   ├── paper-patches/features/       # patches applied on top of paper-api source
│   └── src/main/java/                # OmniPaper's own API classes
│       └── io/github/omnipaper/api/
│           └── event/ServerTickEndEvent.java
└── omnipaper-server/
    ├── build.gradle.kts.patch        # patches Paper's server build file
    ├── minecraft-patches/features/   # NMS-layer patches (net/minecraft/*)
    ├── paper-patches/features/       # CraftBukkit-layer patches (org/bukkit/craftbukkit/*)
    └── src/main/java/                # OmniPaper's own server classes
        └── io/github/omnipaper/server/
            ├── config/               # OmniPaperConfigurations + OmniPaperGlobalConfig
            └── command/              # OmniPaperCommand (/omnipaper)
```

## Patches

### omnipaper-server/minecraft-patches/ (65 total)

| # | Name | Source |
|---|------|--------|
| 0001 | OmniPaper config wiring | OmniPaper |
| 0002 | OmniPaper rebrand (NMS) | OmniPaper |
| 0003 | Adaptive tick rate under load | OmniPaper |
| 0004 | Faster floating-point positive modulo | Gale/Leaf |
| 0005 | Reduce projectile chunk loading | Gale/Leaf |
| 0006 | Move random tick random | Gale/Leaf |
| 0007 | Optimize random calls in chunk ticking | Airplane/Pufferfish/Leaf |
| 0008 | Reduce enderman teleport chunk lookups | Gale/Leaf |
| 0009 | Check targeting range before getting visibility | Gale/Leaf |
| 0010 | Cache on-climbable check | Gale/Leaf |
| 0011 | Make EntityCollisionContext a live representation | Gale/Leaf |
| 0012 | Better checking for useless move packets | Gale/Leaf |
| 0013 | Remove lambda from ticking guard | Gale/Leaf |
| 0014 | Reduce in-wall checks | Gale/Leaf |
| 0015 | Optimize sun-burn tick | Gale/Leaf |
| 0016 | Reduce lambda/Optional allocation in EntityBasedExplosion | Gale/Leaf |
| 0017 | Cache FluidOcclusionCacheKey hash | Gale/Leaf |
| 0018 | Cache ShapePairKey hash | Gale/Leaf |
| 0019 | Replace division by multiplication in CubePointRange | Gale/Leaf |
| 0020 | Check frozen ticks before landing block | Gale/Leaf |
| 0021 | Update boss bar within tick | Gale/Leaf |
| 0022 | Cache world generator sea level | Gale/Leaf |
| 0023 | Skip secondary POI sensor if absent | Gale/Leaf |
| 0024 | Skip entity move if movement is zero | Leaf |
| 0025 | Store mob counts in an array | Leaf |
| 0026 | Optimize noise generation | C2ME/Gale/Leaf |
| 0027 | Hide flames on entities with fire resistance | Leaf |
| 0028 | Skip cloning advancement criteria | Leaf |
| 0029 | Reduce block destruction packet allocations | Leaf |
| 0030 | Send multiple keep-alive packets | Leaf |
| 0031 | Don't load chunks to spawn phantoms | Leaf |
| 0032 | Don't load chunks to activate climbing entities | Leaf |
| 0033 | Prevent entities strolling into non-ticking chunks | Leaf |
| 0034 | Collision: check hasPhysics before same-vehicle | Leaf |
| 0035–0065 | Additional Leaf/Gale/Lithium/Pluto optimizations | Various |

### omnipaper-server/paper-patches/ (35 total)

| # | Name | Source |
|---|------|--------|
| 0001 | OmniPaper rebrand (Paper layer) | OmniPaper |
| 0002 | OmniPaper commands | OmniPaper |
| 0003 | CraftBukkit UUID-to-world map | Leaf |
| 0004 | Pre-compute VarLong sizes | Velocity/Gale/Leaf |
| 0005 | Optimize VarInt/VarLong write | Velocity/Gale/Leaf |
| 0006–0009 | Virtual thread support (4 components) | Gale/Leaf |
| 0010 | Skip event firing if no listeners | Leaf |
| 0011 | SparklyPaper optimize canSee checks | SparklyPaper/Leaf |
| 0012 | Faster random generator | Leaf |
| 0013 | Multithreaded entity tracker | Petal/Airplane/Leaf |
| 0014 | Don't use snapshots for acquiring blockstate | EMC/Leaf |
| 0015 | Faster CraftServer.getWorlds() | Leaf |
| 0016 | Cache chunk key | Leaf |
| 0017 | Fix MC-117075 block entities unload lag spike | Paper PR/Leaf |
| 0018 | Throttle failed spawn attempts | Paper PR/Leaf |
| 0019 | Async player data saving | Leaf |
| 0020 | Async chunk sender | Leaf |
| 0021 | Optimise player movement checks | Leaf |
| 0022 | Optimise ReferenceList | Leaf |
| 0023 | Cache getBiome | Leaf |
| 0024 | Paw optimization | Leaf |
| 0025 | Optimize despawn | Leaf |
| 0026 | Optimize mob spawning | Leaf |
| 0027 | Remove stream in CraftWorld.spawnParticle | Leaf |
| 0028 | Better Spark integration | Leaf |
| 0029 | Use optimized collections in CB classes | Leaf |
| 0030 | Fix MC-301114 combat tracker memory leak | Leaf |
| 0031 | Lazy set Entity.projectileSource | Paper PR/Leaf |
| 0032 | Options to disable chunk/block-entity listeners | Paper PR/Leaf |
| 0033 | Create PDC tag only when necessary | Leaf |

### omnipaper-api/paper-patches/ (7 total)

| # | Name | Source |
|---|------|--------|
| 0001 | OmniPaper rebrand (BRAND_OMNIPAPER_ID) | OmniPaper |
| 0002 | Specific-interval TPS API | Slice/Leaf |
| 0003 | 5-second TPS average | Leaf |
| 0004 | Last tick time API | Leaf |
| 0005 | Smooth teleports | Slice/Leaf |
| 0006 | PlayerInventoryOverflowEvent | Leaf |
| 0007 | Cache NamespacedKey toString and hash | Leaf |

## OmniPaper's own additions

**Config** (`config/omnipaper.yml`): YAML config with sections for `performance`, `async`, `network`, `misc`. Load on startup, hot-reload via `/omnipaper reload`.

**Adaptive tick rate** (minecraft-patch 0003): Rolling 20-tick window; backs off effective TPS target when sustained overload is detected, recovering linearly. Prevents catch-up spirals.

**ServerTickEndEvent**: Async Bukkit event fired after each tick with current tick duration, 1s TPS, and 1-minute TPS rolling average. Opt-in by registering a listener.

## Building

Requires Java 21 and Git.

```bash
git clone https://github.com/your-org/OmniPaper
cd OmniPaper
./gradlew applyPatches
./gradlew createMojmapBundlerJar
```

## Attribution

- [PaperMC](https://github.com/PaperMC/Paper) — base server (GPL-3.0)
- [Leaf (Winds-Studio)](https://github.com/Winds-Studio/Leaf) — optimization patches, build system reference (GPL-3.0)
- [Gale (GaleMC)](https://github.com/GaleMC/Gale) — NMS optimizations (GPL-3.0)
- [Airplane (TECHNOVE)](https://github.com/TECHNOVE/Airplane) — mob/chunk optimizations (GPL-3.0)
- [Pufferfish](https://github.com/pufferfish-gg/Pufferfish) — spawning optimizations (GPL-3.0)
- [SparklyPaper](https://github.com/SparklyPower/SparklyPaper) — canSee optimization
- [Lithium (CaffeineMC)](https://github.com/CaffeineMC/lithium-fabric) — hash palette, heightmap (MIT)
- [Velocity](https://github.com/PaperMC/Velocity) — VarInt/VarLong optimizations (GPL-3.0)

All patch headers preserve original author attributions.
