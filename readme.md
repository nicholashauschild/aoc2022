# Advent Of Code 2022
Advent of Code is a coding challenge. See [adventofcode.com](https://adventofcode.com) for details.

## Using this repo
This year, I am using Kotlin on JetBrains Fleet IDE. While I am using Fleet's 'Run & Debug' configurations (`.fleet/run.json`),
the build process that is executing the kotlin runtime is Gradle (the wrapper), which is included in this repo.

#### Running with gradle on mac/linux:
```commandline
./gradlew -Pday=<day#>
```

#### Running with gradle on windows:
```commandline
gradlew.bat -Pday=<day#>
```

#### Running using Fleet:
Run -> Run & Debug -> day<day#>