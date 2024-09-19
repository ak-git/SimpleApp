# Simple Java "Hello World" Application with Gradle and Docker support

## Update dependencies

### Plugin [com.github.ben-manes.versions](https://github.com/ben-manes/gradle-versions-plugin)

```shell
./gradlew dependencyUpdates
```

## Check dependencies

### Plugin [dependency-analysis-gradle-plugin](https://github.com/autonomousapps/dependency-analysis-gradle-plugin)

```shell
./gradlew buildHealth
```

## Build docker image

```shell
docker build -t a002k/simple-app:latest .
```

## Run docker image

```shell
docker run --rm a002k/simple-app
```

## Run docker image as docker-compose

```shell
docker compose run --rm simple-app
```

or

```shell
docker compose up -d
```

and show logs

```shell
docker compose logs
```

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f7e44f2278c24e49927d582424b0e2a4)](https://app.codacy.com/gh/ak-git/SimpleApp?utm_source=github.com&utm_medium=referral&utm_content=ak-git/SimpleApp&utm_campaign=Badge_Grade_Settings)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=bugs)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
