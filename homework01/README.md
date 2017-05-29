## HOMEWORK1. External libraries.

### MAVEN

``` sh
mvn clean package
cd target/
java -jar homework1-1.0-jar-with-dependencies.jar <your number>
```

#### OR

``` sh
mvn clean package
cd target/
java -cp .:$HOME/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:homework1-1.0.jar package1.package2.package3.SimpleIsNumberCheck <your number>
```

### GRADLE

``` sh
gradle clean jar fatJar
cd build/libs/
java -jar homework1-jar-with-dependencies-1.0.jar  <your number>
```

#### OR

``` sh
gradle clean jar fatJar
cd build/libs/
java -cp .:$HOME/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:homework1-1.0.jar package1.package2.package3.SimpleIsNumberCheck <your number>
```
